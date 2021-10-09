自动化测试框架可以用于
- 自动克隆项目
- 编译项目
- 执行项目
- 评分（需要实现自定义接口）
- 性能测试（需要规定在同一个人的电脑上执行所有同学的程序，否则会因为电脑配置不一样导致结果不一样）
- 生成CSV格式报表（需要实现自定义接口）
- 查重（需要申请moss账户，且只支持特定的语言：c, cc, java, ml, pascal, ada,lisp, schema, haskell, fortran, ascii, vhdl, perl, matlab, python, mips, prolog, spice, vb, csharp, modula2, a8086, javascript,plsql)


目前，项目代码已经分好模块，不仅可以测试[WordCount作业](https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11672)，对于固定输入输出的个人作业都可以支持，不过有了自动化测试工具，助教还需要做的事情是：

0. 作业要求中必须规定好语言版本，如果要支持查重，语言只能限制在moss支持的语言列表中。
1. 自己实现一版个人作业作为对数器。
2. 自己写一个程序来自动生成测试数据，因为每个个人作业的要求不一样，自动化框架无法对每种程序都生成测试数据，目前只支持生成按规则生成随机的字符串测试集。
3. 要制定评分规则（自动化框架已经预留了接口，实现Judge接口即可）
4. 制定最后报表的格式（自动化框架已经预留了接口，实现ReportData接口即可）
5. 申请一个moss账号，用于查重，具体可见：[Use MOSS On Your Computer- 如何使用MOSS代码剽窃检测系统](https://zhuanlan.zhihu.com/p/51875240)



## 代码结构说明

- autotest
    - autotest-core  
      > 框架核心代码，预留接口以及扩展点，常用工具类
    - app-wordcount
      > wordcount作业测试项目，实现了wordcount测试的数据集准备，评分规则，导出csv报表规则，这个模块依赖于autotest-core
    
后续每次增加一个个人作业，都可以以app-wordcount为例，新建一个maven模块，命名为: app-xxx，其中xxx就是个人作业的具体名称。这个app-xxx模块依赖autotest-core模块，实现其接口即可。

下面以自动化测试[WordCount作业](https://edu.cnblogs.com/campus/fzu/FZUSESPR21/homework/11672)为例，来说明如何使用自动化测试框架

## 支持的语言

> 理论上可以支持C++，Java，Python，NodeJS 任意版本的程序，因为测试程序中可以配置不同语言的执行环境

以下是我们测试通过后的语言版本

| 语言   | 版本             |
| ------ | ---------------- |
| Java   | 1.8 |
| C++    | 3.9.2 |
| Python | gc++ 6.3.0           |
| NodeJS | v10.15.3           |

## 实现的功能

### 克隆项目

从指定仓库克隆项目，由于访问Github的网络经常不稳定，也支持我们先预备好项目的仓库，不从Github实时下载。

### 生成测试数据

- 可以生成指定长度的随机ASCII码字符串
- 可以将我们指定长度的测试数据写入指定位置的指定数量的文本文件中，这些文本文件将作为后续的测试用例文件。

注：这里针对不同的项目需要生成的测试数据不一样，wordcount需要生成的数据

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

将：/resources/config.default文件复制一份，重命名为：config.properties

关注并确认config.properties中的如下几个参数，其他参数可以参考注释进行修改：

```properties
# 默认测试用例的数量
TESTCASE_NUM=10
# 测试文本的最少字符数
TEXT_MIN_LENGTH=100
# 测试文本的最大字符数量
TEXT_MAX_LENGTH=1000000
# 是否需要对数程序解答，如果准备好了case和答案，则可以把这个选项设置为false
NEED_ANSWER=true
# 是否需要克隆，如果设置为true，则会使用CLONE_URL到一个目录进行操作
# 如果设置为false，则会使用LOCAL_URI
NEED_CLONE=false
# 需要clone的学生仓库地址
CLONE_URL=https://github.com/kofyou/PersonalProject-Java.git
# 本地准备好的仓库地址：例如: "D:\\git\\WordCountAutoTest\\download\\1615421924089\\PersonalProject-Java"
# 同时需要在这个仓库的父目录，即："D:\\git\\WordCountAutoTest\\download\\1615421924089" 新建两个文件夹，分别是cases和answers
# 并且在cases文件夹和answers文件夹准备好TESTCASE_NUM数量的测试用例和对应答案，文件名称从1.txt,2.txt ... n.txt 开始命名
# 比如TESTCASE_NUM = 3, 那么
# D:\\git\\WordCountAutoTest\\download\\1615421924089\\cases 下有三个txt文件: 1.txt, 2.txt, 3.txt
# D:\\git\\WordCountAutoTest\\download\\1615421924089\\answers 下也有三个txt文件，1.txt, 2.txt, 3.txt 分别对应cases下面的三个文件的答案
LOCAL_URI=C:\\git\\autotest\\download\\1616838383549\\PersonalProject-Java
JUDGE_PROGRAM=C:\\git\\autotest\\download\\judge
```

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

## 查重

使用的是[moss](http://theory.stanford.edu/~aiken/moss/) 组件，需要申请一个moss账号

使用方法，参考：

QuickStart.java




## 源码地址

[Github](https://github.com/GreyZeng/autotest)

[CSDN](https://codechina.csdn.net/hotonyhui/autotest)
