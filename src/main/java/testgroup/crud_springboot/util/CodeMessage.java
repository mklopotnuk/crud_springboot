package testgroup.crud_springboot.util;

public class CodeMessage {
    private static CodeError codeError;
    private static CodeAccess codeAccess;

    public static CodeError getCodeError(){
        CodeError code = codeError;
        codeError = null;
        return code;
    }

    public static CodeAccess getCodeAccess(){
        CodeAccess code = codeAccess;
        codeAccess = null;
        return code;
    }


    public static void setCodeError(CodeError codeError) {
        CodeMessage.codeError = codeError;
    }

    public static void setCodeAccess(CodeAccess codeAccess) {
        CodeMessage.codeAccess = codeAccess;
    }
}
