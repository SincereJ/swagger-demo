package com.example.swagger.swagger;

//import org.apache.commons.lang.StringUtils;
import org.objectweb.asm.Opcodes;

//import com.FieldInfo;
//import com.SimpleJbean;
 
/**
 * SimpleJbean.java. 2011-12-28下午4:12:18 @author LionBule.
 */
public class SimpleJbeanAsm extends SimpleJbean implements Opcodes {
 
    /*@Override
    public byte[] createBeanClass(String className, List<FieldInfo> fields) {
        ClassWriter cw = new ClassWriter(0);
 
        cw.visit(V1_1, ACC_PUBLIC, className, null, "java/lang/Object", null);
 
        // creates a MethodWriter for the (implicit) constructor
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(1, 1);
 
        // create set&get methods
        for (FieldInfo f : fields) {
            addMethod(cw, mv, className, f);
        }
 
        return cw.toByteArray();
    }*/
 
    /*private void addMethod(ClassWriter cw, MethodVisitor mv, String className,
                                  FieldInfo fieldInfo) {
        String fieldName = fieldInfo.name;
        String setMethodName = "set" + StringUtils.capitalize(fieldName);
        String getMethodName = "get" + StringUtils.capitalize(fieldName);
 
        String typeof = Type.getType(fieldInfo.type).getDescriptor();
        String getof = getof(typeof);
        String setof = setof(typeof);
        int[] loadAndReturnOf = loadAndReturnOf(typeof);
        
        //add field
        cw.visitField(ACC_PRIVATE, fieldName, typeof, null, 0).visitEnd();
 
        // getMethod
        mv = cw.visitMethod(ACC_PUBLIC, getMethodName, getof, null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, className, fieldName, typeof);
        mv.visitInsn(loadAndReturnOf[1]);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
        
        // setMethod
        mv = cw.visitMethod(ACC_PUBLIC, setMethodName, setof, null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitVarInsn(loadAndReturnOf[0], 1);
        mv.visitFieldInsn(PUTFIELD, className, fieldName, typeof);
        mv.visitInsn(RETURN);
        mv.visitMaxs(3, 3);
        mv.visitEnd();
    }*/
 
    private String setof(String typeof) {
        return "(" + typeof + ")V";
    }
 
    private String getof(String typeof) {
        return "()" + typeof;
    }
    
    private int[] loadAndReturnOf(String typeof) {
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
 
}