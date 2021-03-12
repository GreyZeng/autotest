## 自动化测试程序（基于WordCount作业）

本自动化测试的程序用于自动化测试[WordCount作业](https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11672)，采用Java开发（基于jdk1.8+），基于Maven来管理项目。



## 支持的语言和开发进度

| 语言   | 进度             |
| ------ | ---------------- |
| Java   | 已测试并投入运行 |
| C++    | 开发完毕，测试中 |
| Python | 已测试并投入运行           |
| NodeJS | 待开发           |



## 实现的功能

### 克隆项目

从指定仓库克隆项目，由于访问Github的网络经常不稳定，也支持我们先预备好项目的仓库，不从Github实时下载。

### 生成测试数据

- 可以生成指定长度的随机ASCII码字符串
- 可以将我们指定长度的测试数据写入指定位置的指定数量的文本文件中，这些文本文件将作为后续的测试用例文件。

### 编译

这里的编译和以下的运行都是有如下两个前置要求：

1. 作业中必须明确要求入口文件的文件名是什么，以Java为例，就是Main方法所在的类文件的文件名是什么，以WordCount作业为例，我们要求学生的主函数必须定义在src目录下一个名叫WordCount.java文件中，因为这样我们才知道要运行哪个文件来执行测试用例。
2. 助教在自己机器上运行的时候，必须要有对应语言的编译和运行的环境且要规定好一致的语言版本。否则编译这一关会有很多问题导致无法运行学生的代码。

编译时候会设置对应的超时时间，不同的语言可以设置不一样的编译超时时间，以Java为例，默认编译超时时间是5秒钟

### 运行

见编译部分提到的两点要求。

运行的时候也会设置对应的超时时间，不同的语言针对不一样的测试用例可以设置不一样的运行时间，超过这个时间，会直接将学生这个用例的耗时数设置为-2，-2表示耗时的记录。

### 评分

- 每个用例的得分以及汇总得分
- 执行每个用例的耗时

注：我们统计的耗时是运行部分的耗时，不包括编译的耗时。

### 导出结果到CSV

- 分数
- 耗时
- Git提交记录
  - 提交次数
  - 每次提交的commit信息

## 效果预览

![](https://img2020.cnblogs.com/blog/683206/202103/683206-20210311160102326-358774870.png)

其中：

StudentNo: 表示学生学号的后五位

Score：汇总分数，即Score1 + Score2 + … + Scorei 之和

Scorei：表示第i个用例的得分

Timei：表示第i个用例的耗时

commit_times：表示每个学生的提交次数

commit_details：表示每个学生的提交信息，JSON格式



## 使用方式

> 目前没有将项目打包，还是以源码的方式运行，主要是方便调试和改代码，后续会完善打包运行。



我们必须先规定好测试代码的位置，以WordCount项目为例，所有同学的代码都以学号命名收集到如下仓库中：

https://github.com/kofyou/PersonalProject-Java.git

目录结构为：

PersonalProject-Java

- 学号1
  - src
    - WordCount.java
- 学号2
  - src
    - WordCount.java
    - Lib.java
- 学号3
  - src
    - WordCount.java

…



克隆代码

```bash
git clone https://github.com/GreyZeng/WordCountAutoTest.git
```



克隆完毕后，用Jetbrains IDEA 打开文件WordCountAutoTest，并且在WordCountAutoTest下新建download文件夹，如下图：

![](https://img2020.cnblogs.com/blog/683206/202103/683206-20210311150843165-514756181.png)

在download文件夹下新建一个judge文件夹，里面放对数程序，这个对数程序的目的就是，把测试用例的答案算出来，

**这个对数程序要保证正确！！！最好多个助教一起做一下题目，然后互相验证没问题了，在把对数程序放上去**。

**PS：对数程序请不要提交到Github，防止学生抄袭！**

如上图，我放了一个对数程序WordCount.java



关注并确认Client.java类中的如下几个参数，其他参数可以参考注释进行修改，一般不需要改：

| 参数名称        | 备注                                                         |
| --------------- | ------------------------------------------------------------ |
| NEED_CLONE      | 设置为true，会不断重试clone代码仓库                          |
| PYTHON_EXE_LOCATION      | python.exe的绝对路径,例如：C:\\Program Files\\Python39\\python.exe                         |
| CLONE_URL       | 改成对应的地址，例如：https://github.com/kofyou/PersonalProject-Java.git |
| JUDGE_PROGRAM   | 改成对数程序的绝对路径地址，例如："D:\\git\\WordCountAutoTest\\download\\judge" |
| TESTCASE_NUM    | 默认测试用例的数量，默认10个                                 |
| TEXT_MIN_LENGTH | 测试文本的最少字符数，默认100个字符                          |
| TEXT_MAX_LENGTH | 测试文本的最大字符数量，默认1000000个字符                    |



修改好配置参数后，直接在IDEA里面Run Client.java，等待执行完毕即可。



> 说明：运行的时候，会在之前download的目录以当前时间戳建一个文件夹，这样做的目的是保证每次运行不会有文件夹冲突。



执行完毕后，按如下目录找需要的信息：

| 目录                                              | 说明                                 |
| ------------------------------------------------- | ------------------------------------ |
| download/时间戳/cases/                            | 存放用例的位置                       |
| download/时间戳/answers/                          | 存放答案的位置                       |
| download/时间戳/PersonalProject-Java/             | 项目目录                             |
| download/时间戳/PersonalProject-Java/学号/output/ | 每个学号的学生的执行用例的输出文件夹 |
| download/时间戳/result/result.csv                 | 本次测评的csv文件                    |
| WordCountAutoTest\log                             | 日志记录文件夹                       |



## 待完善的功能

- Git的每次签入详情，代码的修改和新增情况。
- 代码雷同部分，尝试接入[moss](http://theory.stanford.edu/~aiken/moss/) 。
- 防止代码里面修改服务器文件，恶意运行多线程 ，参考[Judger](https://github.com/QingdaoU/Judger) 。

## 源码地址

[Github](https://github.com/GreyZeng/WordCountAutoTest)




