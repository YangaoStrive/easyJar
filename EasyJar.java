import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class EasyJar extends JFrame {
    public static void main(String[] args) {
        // 创建窗体对象
        Frame f = new Frame("打包");
        // 创建平行箱体
        // jar包路径
        Box horizontalBox1 = Box.createHorizontalBox();
        // 打包路径
        Box horizontalBox2 = Box.createHorizontalBox();
        // 压缩包名
        Box horizontalBox3 = Box.createHorizontalBox();
        // 发版路径
        Box horizontalBox4 = Box.createHorizontalBox();
        // 写入配置文件
        Box horizontalBox5 = Box.createHorizontalBox();
        // 生成配置文件
        Box horizontalBox6 = Box.createHorizontalBox();
        // 创建垂直箱体
        Box verticalBox = Box.createVerticalBox();

        // 设置窗体属性和布局
        f.setBounds(400, 200, 600, 700);
        f.setLayout(new BoxLayout(f, BoxLayout.Y_AXIS));

        // 创建文本框
        final TextField tf1 = new TextField(); // jar包所在路径文本框
        final TextField tf2 = new TextField(); // 打包路径文本框
        final TextField tf3 = new TextField(); // 压缩包名称文本框
        final TextField tf4 = new TextField(); // 发版路径的文本框

        // 创建文件选择器
        // jar包所在路径
        JFileChooser jFileChooser1 = new JFileChooser(new File("C:\\"));
        jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 打包路径
        JFileChooser jFileChooser2 = new JFileChooser(new File("C:\\"));
        jFileChooser2.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 发版路径
        JFileChooser jFileChooser3 = new JFileChooser(new File("C:\\"));
        jFileChooser3.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        // 配置文件
        JFileChooser jFileChooser4 = new JFileChooser(new File(".\\"));

        // 添加文本域（用来显示日志信息）
        JTextArea jTextArea = new JTextArea();
        jTextArea.setColumns(5);
        jTextArea.setSelectionStart(jTextArea.getText().length());
        // 创建按钮
        Button bu = new Button("RUN！！！"); // 执行按钮
        Button bu1 = new Button("searchJar"); // jar包所在路径按钮
        Button bu2 = new Button("jarPath"); // jar包压缩路径按钮
        Button bu3 = new Button("sendPath"); // 发版路径按钮
        Button bu4 = new Button("addMsg"); // 配置文件回填数据按钮
        Button bu5 = new Button("createMsg"); // 生成配置文件按钮

        // 创建文字
        JLabel jLabel1 = new JLabel("jar包所在路径:");
        JLabel jLabel2 = new JLabel("发送到的打包路径:");
        JLabel jLabel3 = new JLabel("压缩包名称:");
        JLabel jLabel4 = new JLabel("发版路径:");
        JLabel jLabel5 = new JLabel("日志:");
        JLabel jLabel6 = new JLabel("根据配置文件生成数据:");

        // 创建空格窗体
        Component horizontalStrut1 = Box.createHorizontalStrut(50);
        Component horizontalStrut2 = Box.createHorizontalStrut(25);
        Component horizontalStrut3 = Box.createHorizontalStrut(20);
        Component horizontalStrut4 = Box.createHorizontalStrut(77);
        Component horizontalStrut5 = Box.createHorizontalStrut(18);
        Component horizontalStrut6 = Box.createHorizontalStrut(10);

        // 平行箱体添加模块
        horizontalBox1.add(jLabel1);
        horizontalBox1.add(horizontalStrut1);
        horizontalBox1.add(bu1);

        horizontalBox2.add(jLabel2);
        horizontalBox2.add(horizontalStrut2);
        horizontalBox2.add(bu2);

        horizontalBox3.add(jLabel3);
        horizontalBox3.add(horizontalStrut3);

        horizontalBox4.add(jLabel4);
        horizontalBox4.add(horizontalStrut4);
        horizontalBox4.add(bu3);

        horizontalBox5.add(jLabel6);
        horizontalBox5.add(horizontalStrut5);
        horizontalBox5.add(bu5);
        horizontalBox5.add(horizontalStrut6);
        horizontalBox5.add(bu4);

        // 把组件添加到窗体
        Component verticalStrut1 = Box.createVerticalStrut(10);
        Component verticalStrut2 = Box.createVerticalStrut(10);
        Component verticalStrut3 = Box.createVerticalStrut(10);
        Component verticalStrut4 = Box.createVerticalStrut(10);
        Component verticalStrut5 = Box.createVerticalStrut(10);

        // 将平行箱体添加到垂直箱体中
        verticalBox.add(horizontalBox1);
        verticalBox.add(tf1);
        verticalBox.add(verticalStrut1);
        verticalBox.add(horizontalBox2);
        verticalBox.add(tf2);
        verticalBox.add(verticalStrut2);
        verticalBox.add(horizontalBox3);
        verticalBox.add(tf3);
        verticalBox.add(verticalStrut3);
        verticalBox.add(horizontalBox4);
        verticalBox.add(tf4);
        verticalBox.add(verticalStrut4);
        verticalBox.add(horizontalBox5);
        verticalBox.add(verticalStrut5);
        verticalBox.add(bu);
        verticalBox.add(jLabel5);
        verticalBox.add(jTextArea);
        // 将箱体添加到jFrame窗口框架里
        f.add(verticalBox);
        // 设置窗体关闭
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // 选择jar包按钮的点击配置
        bu1.addActionListener(e -> {
            int status = jFileChooser1.showOpenDialog(jFileChooser1);
            if (status != JFileChooser.APPROVE_OPTION) {
                jTextArea.setText("没有选中文件");
            } else {
                // 选择的文件对象
                File selectedFile = jFileChooser1.getSelectedFile();
                if (selectedFile.isDirectory()) {
                    String path = selectedFile.getPath();
                    tf1.setText(path);
                } else {
                    jTextArea.setText("请选择文件夹！");
                }
            }

        });
        // 选择打包路径的按钮点击配置
        bu2.addActionListener(e -> {
            int status = jFileChooser2.showOpenDialog(jFileChooser2);
            if (status != JFileChooser.APPROVE_OPTION) {
                jTextArea.setText("没有选中文件");
            } else {
                // 选择的文件对象
                File selectedFile = jFileChooser2.getSelectedFile();
                if (selectedFile.isDirectory()) {
                    String path = selectedFile.getPath();
                    tf2.setText(path);
                } else {
                    jTextArea.setText("请选择文件夹！\r\n");
                }
            }
        });
        // 选择发版路径的点击配置
        bu3.addActionListener(e -> {
            int status = jFileChooser3.showOpenDialog(jFileChooser3);
            if (status != JFileChooser.APPROVE_OPTION) {
                jTextArea.setText("没有选中文件!\r\n");
            } else {
                // 选择的文件对象
                File selectedFile = jFileChooser3.getSelectedFile();
                if (selectedFile.isDirectory()) {
                    String path = selectedFile.getPath();
                    tf4.setText(path);
                } else {
                    jTextArea.setText("请选择文件夹！\r\n");
                }
            }

        });
        // 根据配置文件回填数据的按钮点击配置
        bu4.addActionListener(e -> {
            int status = jFileChooser4.showOpenDialog(jFileChooser4);
            if (status != JFileChooser.APPROVE_OPTION) {
                jTextArea.setText("没有选中文件!\r\n");
            } else {
                File selectedFile = jFileChooser4.getSelectedFile();
                if (selectedFile.getName().contains("Config")) {
                    try {
                        // 根据行数填写队形的数据（从行数1开始）
                        InputStreamReader bos = new InputStreamReader(new FileInputStream(selectedFile), "GBK");
                        Scanner sc = new Scanner(bos);
                        int i = 1;
                        while (sc.hasNextLine()) {
                            String s = sc.nextLine();
                            switch (i) {
                                case 1:
                                    tf1.setText(s);
                                    i++;
                                    break;
                                case 2:
                                    tf2.setText(s);
                                    i++;
                                    break;
                                case 3:
                                    tf3.setText(s);
                                    i++;
                                    break;
                                case 4:
                                    tf4.setText(s);
                                    i++;
                                    break;
                                default:
                                    i = 1;
                                    break;
                            }
                        }
                        bos.close();
                    } catch (Exception ex) {
                        jTextArea.setText("根据配置文件添加数据异常！\r\n" + "异常为：" + ex.toString());
                    }
                } else {
                    jTextArea.setText("没有选中config文件，请重新选择！\r\n");
                }
            }
        });
        // 生成配置文件的点击配置
        bu5.addActionListener(e->{
            // 获取jar包所在路径
            String jarPath = tf1.getText().trim();
            // 获取打包路径
            String packPath = tf2.getText().trim();
            // 获取压缩包名
            String zipName = tf3.getText().trim();
            // 获取发版路径
            String devPath = tf4.getText().trim();

            if (jarPath.equals("")) {
                jTextArea.setText("jar包路径为空！\r\n");
                return;
            }
            if (packPath.equals("")) {
                jTextArea.setText("打包包路径为空！\r\n");
                return;
            }
            if (zipName.equals("")) {
                jTextArea.setText("压缩文件名为空！\r\n");
                return;
            }

            File file = new File("urlConfig.txt");
            if (file.exists()) {
                try {
                    addInfoFile(jarPath, packPath, zipName, devPath);
                } catch (IOException ioException) {
                    jTextArea.setText("更新配置文件出现异常！\r\n" + "异常为：" + ioException.toString());
                }
                jTextArea.setText("文件更新成功！\r\n" + "地址为：" + file.getPath());
            } else {
                try {
                    addInfoFile(jarPath, packPath, zipName, devPath);
                    jTextArea.setText("文件生成成功！\r\n" + "地址为：" + file.getPath());
                } catch (IOException ioException) {
                    jTextArea.setText("生成配置文件出现异常！\r\n" + "异常为：" + ioException.toString());
                }
            }
        });
        // 对按钮添加事件
        bu.addActionListener(e -> {
            jTextArea.setText("");
            // 获取jar包所在路径
            String jarPath = tf1.getText().trim();
            // 获取打包路径
            String packPath = tf2.getText().trim();
            // 获取压缩包名
            String zipName = tf3.getText().trim();
            // 获取发版路径
            String devPath = tf4.getText().trim();

            // new File对象
            File jarPathFile = new File(jarPath);
            // bat文件的位置
            String batLocal = "zip.bat";
            File batfile = new File(batLocal);
            // 如果文件存在
            if (!batfile.exists()) {
                try {
                    batfile.createNewFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            Map map = new HashMap();
            // 遍历获取出jar包名称和jar包路径
            Map jarInfo = getJarInfo(map, jarPathFile);
            Set<Map.Entry> set = jarInfo.entrySet();
            for (Map.Entry entry : set) {
                // jar包名称
                String name = (String) entry.getKey();
                // jar包路径
                String path = (String) entry.getValue();
                String newpackPath = packPath + "/" + name;
                File jarfile = new File(path);
                File newpackPathFile = new File(newpackPath);
                boolean isSuccess;
                // 判断要复制的路径中是否有这个文件
                if (newpackPathFile.exists()) {
                    // 删除
                    newpackPathFile.delete();
                }
                // 1. 移动文件成功
                isSuccess = jarfile.renameTo(new File(newpackPath));
                if (isSuccess) {
                    jTextArea.append(name + "文件移动成功" + "  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    jTextArea.append("\r\n");
                    jTextArea.append("初始路径为：" + jarfile);
                    jTextArea.append("\r\n");
                    jTextArea.append("目标路径为：" + newpackPath);
                    jTextArea.append("\r\n");
                    jTextArea.append("\r\n");
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("7z a ");
            // 拼接zip路径
            String zipPath = packPath + "\\" + zipName + " ";
            stringBuilder.append(zipPath);
            // 2. new打包路径的File
            File packPathFile = new File(packPath);
            File[] packPathFiles = packPathFile.listFiles();
            for (File pathFile : packPathFiles) {
                String name = pathFile.getName();
                if (name.contains(".zip")) {
                    if (name.equals(zipName)) {
                        pathFile.delete();
                    }
                }
                if (name.contains(".jar")) {
                    stringBuilder.append(packPath + "\\" + name + " ");
                }
            }
            stringBuilder.append("\r\n");
            stringBuilder.append("exit");
            // 3.写入bat脚本
            try {
                // 写如脚本文件中（必须要用GBK编码，要不然打包会失败）
                OutputStreamWriter bos = new OutputStreamWriter(new FileOutputStream("zip.bat"), "GBK");
                //写数据
                bos.write(stringBuilder.toString());
                //释放资源
                bos.close();
            } catch (IOException ioException) {
                jTextArea.setText("写入bat异常：" + ioException.toString());
            }
            //4. 执行bat脚本
            Runtime runtime = Runtime.getRuntime();
            try {
                Process exec = runtime.exec("cmd /c start zip.bat");
                exec.waitFor();
            } catch (IOException | InterruptedException ioException) {
                ioException.printStackTrace();
                jTextArea.setText("执行脚本异常信息:" + ioException.toString());
            }
            // 5. 将zip文件放入发版路径
            File devPathFile = new File(devPath + "\\" + zipName);
            if (devPathFile.exists()) {
                devPathFile.delete();
            }
            try {
                Thread.sleep(3000);
                // zip包执行过后删除，根据需要解开注释
//                File file = new File("zip.bat");
//                file.delete();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            // new一个zipFile
            File zipNameFile = new File(packPath + "\\" + zipName);
            boolean exists = zipNameFile.exists();
            if (!exists) {
                jTextArea.setText("压缩文件未生成" + "\r\n");
            } else {
                boolean b = zipNameFile.renameTo(devPathFile);
                if (b) {
                    jTextArea.append("发版路径文件已生成" + "  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    jTextArea.append("\r\n");
                } else {
                    jTextArea.setText("发版路径文件生成失败");
                    jTextArea.append("\r\n");
                    jTextArea.append("初始路径为：" + zipNameFile);
                    jTextArea.append("\r\n");
                    jTextArea.append("目标路径为：" + devPathFile);
                }
            }
        });

        // 设置窗体显示
        f.setVisible(true);
    }

    /**
     * 递归遍历jar包
     * @param map
     * @param thisfile
     * @return
     */
    public static Map getJarInfo(Map map, File thisfile) {
        File[] files = thisfile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                getJarInfo(map, file);
            } else {
                String name = file.getName();
                String path = file.getPath();
                if (path.contains("\\build\\libs")) {
                    if (name.contains("jar")) {
                        map.put(name, path);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 生成配置文件，可自动填写配置项
     *
     * @param jarPath  jar包所在路径
     * @param packPath 打包路径
     * @param zipName  打包名称
     * @param devPath  发版路径
     * @return
     */
    public static void addInfoFile(String jarPath, String packPath, String zipName, String devPath) throws IOException {
        // 生成文件
        OutputStreamWriter bos = new OutputStreamWriter(new FileOutputStream("urlConfig.txt"), "GBK");
        bos.write(jarPath + "\r\n");
        bos.append(packPath + "\r\n");
        bos.append(zipName + "\r\n");
        bos.append(devPath + "\r\n");
        bos.close();
    }
}
