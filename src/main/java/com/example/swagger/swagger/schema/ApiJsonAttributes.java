/*
 *
 *  Copyright 2015-2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package com.example.swagger.swagger.schema;

import com.example.swagger.swagger.annos.ApiJsonAttribute;
import com.fasterxml.classmate.ResolvedType;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.AllowableRangeValues;
import springfox.documentation.service.AllowableValues;
import springfox.documentation.spring.web.DescriptionResolver;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.util.StringUtils.hasText;

public final class ApiJsonAttributes {
  private static final Logger LOGGER = LoggerFactory.getLogger(ApiJsonAttributes.class);
  private static final Pattern RANGE_PATTERN = Pattern.compile("range([\\[(])(.*),(.*)([])])$");

  private ApiJsonAttributes() {
    throw new UnsupportedOperationException();
  }

  public static Function<ApiJsonAttribute, AllowableValues> toAllowableValues() {
    return new Function<ApiJsonAttribute, AllowableValues>() {
      @Override
      public AllowableValues apply(ApiJsonAttribute annotation) {
        return allowableValueFromString(annotation.allowableValues());
      }
    };
  }

  public static AllowableValues allowableValueFromString(String allowableValueString) {
    AllowableValues allowableValues = new AllowableListValues(Lists.<String>newArrayList(), "LIST");
    String trimmed = allowableValueString.trim();
    Matcher matcher = RANGE_PATTERN.matcher(trimmed.replaceAll(" ", ""));
    if (matcher.matches()) {
      if (matcher.groupCount() != 4) {
        LOGGER.warn("Unable to parse range specified {} correctly", trimmed);
      } else {
        allowableValues = new AllowableRangeValues(
            matcher.group(2).contains("infinity") ? null : matcher.group(2),
            matcher.group(1).equals("("),
            matcher.group(3).contains("infinity") ? null : matcher.group(3),
            matcher.group(4).equals(")"));
      }
    } else if (trimmed.contains(",")) {
      Iterable<String> split = Splitter.on(',').trimResults().omitEmptyStrings().split(trimmed);
      allowableValues = new AllowableListValues(newArrayList(split), "LIST");
    } else if (hasText(trimmed)) {
      List<String> singleVal = Collections.singletonList(trimmed);
      allowableValues = new AllowableListValues(singleVal, "LIST");
    }
    return allowableValues;
  }

  public static Function<ApiJsonAttribute, Boolean> toIsRequired() {
    return new Function<ApiJsonAttribute, Boolean>() {
      @Override
      public Boolean apply(ApiJsonAttribute annotation) {
        return annotation.required();
      }
    };
  }

  public static Function<ApiJsonAttribute, Integer> toPosition() {
    return new Function<ApiJsonAttribute, Integer>() {
      @Override
      public Integer apply(ApiJsonAttribute annotation) {
        return annotation.position();
      }
    };
  }

  static Function<ApiJsonAttribute, Boolean> toAllowEmptyValue() {
    return new Function<ApiJsonAttribute, Boolean>() {
      @Override
      public Boolean apply(ApiJsonAttribute annotation) {
        return annotation.allowEmptyValue();
      }
    };
  }

  public static Function<ApiJsonAttribute, String> toDescription(
      final DescriptionResolver descriptions) {

    return new Function<ApiJsonAttribute, String>() {
      @Override
      public String apply(ApiJsonAttribute annotation) {
        String description = "";
        if (!Strings.isNullOrEmpty(annotation.value())) {
          description = annotation.value();
        } else if (!Strings.isNullOrEmpty(annotation.notes())) {
          description = annotation.notes();
        }
        return descriptions.resolve(description);
      }
    };
  }

  public static Function<ApiJsonAttribute, ResolvedType> toType(final TypeResolver resolver) {
    return new Function<ApiJsonAttribute, ResolvedType>() {
      @Override
      public ResolvedType apply(ApiJsonAttribute annotation) {
        try {
          return resolver.resolve(Class.forName(annotation.dataType()));
        } catch (ClassNotFoundException e) {
          return resolver.resolve(Object.class);
        }
      }
    };
  }

  public static Optional<ApiJsonAttribute> findApiModePropertyAnnotation(AnnotatedElement annotated) {
    Optional<ApiJsonAttribute> annotation = Optional.absent();

    if (annotated instanceof Method) {
      // If the annotated element is a method we can use this information to check superclasses as well
      annotation = Optional.fromNullable(AnnotationUtils.findAnnotation(((Method) annotated), ApiJsonAttribute.class));
    }

    return annotation.or(Optional.fromNullable(AnnotationUtils.getAnnotation(annotated, ApiJsonAttribute.class)));
  }

  public static Function<ApiJsonAttribute, Boolean> toHidden() {
    return new Function<ApiJsonAttribute, Boolean>() {
      @Override
      public Boolean apply(ApiJsonAttribute annotation) {
        return annotation.hidden();
      }
    };
  }

  public static Function<ApiJsonAttribute, String> toExample() {
    return new Function<ApiJsonAttribute, String>() {
      @Override
      public String apply(ApiJsonAttribute annotation) {
        String example = "";
        if (!Strings.isNullOrEmpty(annotation.example())) {
          example = annotation.example();
        }
        return example;
      }
    };
  }
}
