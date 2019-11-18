package com.example.swagger.swagger.config;

import com.example.swagger.swagger.annos.ApiJsonProperty;
import jdk.internal.org.objectweb.asm.*;
import org.apache.commons.lang3.StringUtils;

public class SwaggerASMUtil implements Opcodes {

    private static void createClazz(ClassWriter cw,String className){
        cw.visit(V1_8, ACC_PUBLIC, className, null, "java/lang/Object", null);
    }

    private static void createConstructor(ClassWriter cw){
        MethodVisitor methodVisitor=cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>", "()V", null, null);
        methodVisitor.visitCode();

        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);//0 表示当前对象
        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object", "<init>","()V",false);
        methodVisitor.visitInsn(Opcodes.RETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }

    private static void doParseFieldAndMethod(ClassWriter cw, ApiJsonProperty[] propertys, String className){
        for (ApiJsonProperty property : propertys) {

            String typeof = "";
            if(property.type() != null){
                typeof = Type.getType(property.type()).getDescriptor();
            }
            int[] loadAndReturnOf = loadAndReturnOf(typeof);

            // 创建 字段 和 注释
            createFieldAndAnno(cw,property,typeof);

            // 创建字段getter 方法
            createFieldGetterMethod(cw,property,className,typeof,getOrSetOffer(typeof,true),loadAndReturnOf);

            // 创建字段setter 方法
            createFieldSetterMethod(cw,property,className,typeof,getOrSetOffer(typeof,false),loadAndReturnOf);

        }
    }

    private static void createFieldGetterMethod(ClassWriter cw,ApiJsonProperty property,String className,String typeof, String typeoffer, int[] loadAndReturnOf){
        String getterName = getterAndSetterName(property.key(),true);
        MethodVisitor m_getName=cw.visitMethod(ACC_PUBLIC, getterName, typeoffer, null, null);
        m_getName.visitVarInsn(ALOAD, 0);
        m_getName.visitFieldInsn(GETFIELD, className, property.key(), typeof);
        m_getName.visitInsn(loadAndReturnOf[1]);
        m_getName.visitMaxs(2, 1);
        m_getName.visitEnd();
    }

    private static void createFieldSetterMethod(ClassWriter cw,ApiJsonProperty property,String className,String typeof, String typeoffer, int[] loadAndReturnOf){
        String setterName = getterAndSetterName(property.key(),false);
        MethodVisitor m_setName=cw.visitMethod(ACC_PUBLIC, setterName, typeoffer, null, null);
        m_setName.visitVarInsn(ALOAD, 0);
        m_setName.visitVarInsn(loadAndReturnOf[0], 1);
        m_setName.visitFieldInsn(PUTFIELD, className, property.key(), typeof);
        m_setName.visitInsn(RETURN);
        m_setName.visitMaxs(3,3);
        m_setName.visitEnd();
    }

    private static void createFieldAndAnno(ClassWriter cw, ApiJsonProperty property,String typeof){
        FieldVisitor fv = cw.visitField(ACC_PUBLIC, property.key(), typeof, null, new String(property.example().toString()));
        fv.visitEnd();

        AnnotationVisitor av = fv.visitAnnotation("Lio/swagger/annotations/ApiModelProperty;", true);
        //注释参数
        av.visit("value", property.key());
        av.visit("example", property.example());
        av.visitEnd();
    }

    public static byte[] createRefModel(ApiJsonProperty[] propertys, String className) {
        try {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            //创建类
            createClazz(cw,className);

            //创建构造方法
            createConstructor(cw);

            //循环处理 getter 和 setter 方法 创建字段和注解
            doParseFieldAndMethod(cw,propertys,className);

            cw.visitEnd();

            byte[] code = cw.toByteArray();

            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getterAndSetterName(String name, Boolean isGetter){
        if(name.length() > 1){
            name = StringUtils.capitalize(name);
            if(isGetter) {
                return "get" + name;
            }else{
                return "set" + name;
            }
        }
        return name;
    }

    private static String getOrSetOffer(String typeof, boolean isGet){
        if(isGet){
            return "()" + typeof;
        }
        return "(" + typeof + ")V";
    }

    private static int[] loadAndReturnOf(String typeof) {
        if (typeof.equals("I") || typeof.equals("Z")) {
            return new int[]{ILOAD,IRETURN};
        } else if (typeof.equals("J")) {
            return new int[]{LLOAD,LRETURN};
        } else if (typeof.equals("D")) {
            return new int[]{DLOAD,DRETURN};
        } else if (typeof.equals("F")) {
            return new int[]{FLOAD,FRETURN};
        } else {
            return new int[]{ALOAD,ARETURN};
        }
    }

    public static String returnClassName(String requestMappingPatternName, String name){
        requestMappingPatternName = ("Class"+requestMappingPatternName).replaceAll("/","_");
        return requestMappingPatternName+"_"+name;
    }

}
