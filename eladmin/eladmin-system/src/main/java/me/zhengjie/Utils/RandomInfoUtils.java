package me.zhengjie.Utils;

import org.checkerframework.checker.units.qual.A;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author liukun.inspire
 * @Date 2023/11/12 17:35
 * @PackageName: me.zhengjie.Utils
 * @ClassName: RandomInfoUtils
 * @Version 1.0
 */
public class RandomInfoUtils {


    private static final List<String> protectInfoList = Arrays.asList("Apache许可证", "MIT许可证", "无条件限制许可证", "软件著作保护", "闭源代码");

    static Random random = new Random();

    public static String getProtectInfo() {

        int t = random.nextInt(protectInfoList.size());
        return protectInfoList.get(t);
    }

    private static final List<String> faultList = Arrays.asList("CVE2021", "CVE2022", "Web安全漏洞", "缓冲区溢出", "None");

    public static String getFault() {

        int t = random.nextInt(faultList.size());
        return faultList.get(t);
    }

    private static final List<String> dangerCodeList = Arrays.asList("SQL注入", "XSS注入", "CRSF工具", "木马注入", "勒索程序", "None");

    public static String getDanger() {

        int t = random.nextInt(dangerCodeList.size());
        return dangerCodeList.get(t);
    }

    private static final List<String> functionComesFrom = Arrays.asList("自建数据库", "CVE榜单", "OpenSSL", "GCC", "None");

    public static String getComesFrom() {

        int t = random.nextInt(functionComesFrom.size());
        return functionComesFrom.get(t);
    }

    private static final List<String> compileLevelList = Arrays.asList("O1", "O2", "O3", "O4", "Os", "None");

    public static String getComplieLevel() {

        int t = random.nextInt(compileLevelList.size());
        return compileLevelList.get(t);
    }

    private static final List<String> targetArch = Arrays.asList("X86", "X86_64", "ARM", "ARM_64", "MIPS", "POWER PC");

    public static String getTargetArch() {

        int t = random.nextInt(targetArch.size());
        return targetArch.get(t);
    }

    private static final List<String> obs = Arrays.asList("控制流平坦化", "复杂指令替换", "数据流混淆", "函数重命名", "指令重排", "加壳");


    public static String getTargetObs() {
        int t = random.nextInt(obs.size());
        return obs.get(t);
    }


    public static Integer getRandomCodeLine() {

        return random.nextInt(2000);
    }

    public static Integer getSearchCount() {
        return random.nextInt(1000000);
    }

    public static String getRandomFunctionName() {
        // 定义一组更多的单词，包括形容词、名词、动词
        String[] adjectives = {"Advanced", "Dynamic", "Recursive", "Optimized", "Parallel"};
        String[] nouns = {"Algorithm", "Processor", "Framework", "Pipeline", "Module"};
        String[] verbs = {"Compute", "Process", "Analyze", "Optimize", "Transform"};

        // 创建一个 Random 对象
        // 生成十个更复杂的随机函数名称
        // 从 adjectives、nouns 和 verbs 数组中随机选择一个形容词、一个名词和一个动词
        String randomAdjective = adjectives[random.nextInt(adjectives.length)];
        String randomNoun = nouns[random.nextInt(nouns.length)];
        String randomVerb = verbs[random.nextInt(verbs.length)];

        // 生成随机函数名称，可以按照自己的规则进行组合
        String randomFunctionName = randomAdjective + randomNoun + randomVerb;

        // 打印随机函数名称
        return randomFunctionName;
    }
}
