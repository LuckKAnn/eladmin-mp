package me.zhengjie.Utils;

import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

/**
 * @Author liukun.inspire
 * @Date 2023/10/28 14:42
 * @PackageName: me.zhengjie.Utils
 * @ClassName: PythonUtils
 * @Version 1.0
 */
public class PythonUtils {


    @Data
    public static class ScriptParam {

        private String prefix;

        private String value;

    }


    private static String doProcessScript(List<ScriptParam> scriptParams) {
        StringBuilder sb = new StringBuilder();
        for (ScriptParam scriptParam : scriptParams) {
            sb.append(" --");
            sb.append(scriptParam.getPrefix());
            sb.append(" ");
            sb.append(scriptParam.getValue());
        }
        return sb.toString();
    }

    public static String runPythonScript(String path, List<ScriptParam> scriptParamList) throws Exception {
        // 执行Python文件，并传入参数
        String scriptStr = doProcessScript(scriptParamList);
        String[] args1 = new String[]{"python", path, scriptStr};

        String join = String.join(" ", "python", path, scriptStr);
        System.out.println(join);
        Process proc = Runtime.getRuntime().exec(args1);
        // 获取Python输出字符串作为输入流被Java读取
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String actionStr = in.readLine();

        in.close();
        proc.waitFor();

        return scriptStr;
    }

    public static void main(String[] args) throws Exception {

        ScriptParam scriptParam = new ScriptParam();
        scriptParam.setPrefix("o");
        scriptParam.setValue("/sdasd/sad/as/d/sad/s/ad/as/dsa");

        List<ScriptParam> scriptParams = Collections.singletonList(scriptParam);


        runPythonScript("../test.py", scriptParams);
    }
}
