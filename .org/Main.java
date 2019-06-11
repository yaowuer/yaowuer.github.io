public class Main {
    public static void main (String... args) {

    }

    public String getCmdPath (String dir, String cmd) {
        if (dir.contains(":")) { // 绝对路径
            return getPathFile(cmd);
        } else { // 相对路径
            String fname = dir + "/" + cmd;
            File f = getPathFile(fname);
            if (f != null) { // 是否在当前文件夹下存在对应的文件
                return f.getAbsolutePath();
            } else { // 如果当前目录下没有，那么迭代环境变量查找
                for (String p : System.getenv("path").split(";")) {
                    File f = getPathFile(p + "/" + cmd);
                    if (f.exists()) return f.getAbsolutePath();
                }
                return null;
            }
        }
    }
    private String getPathFile (String path) {
        if (path.contains(".")) {
            File f = new File(path);
            if (f.exists()) return f.getAbsolutePath();
        } else {
            for (String p : Arrays.asAlist(".cmd", ".exe", ".bat")) {
                File f = new File (path + "/" + p);
                if (f.exists()) return f.getAbsolutePath();
            }
        }
        return null;
    }
}
