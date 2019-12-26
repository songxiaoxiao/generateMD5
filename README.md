# 密码生成器-字母数字特字符串各种组合生成
### 背景
    1. 公元1992年美国密码学家罗纳德·李维斯特（Ronald Linn Rivest）公开一种新算法-MD5
    2. 公元2014年，中国学生winter得到一个md5的密钥想要解出来，接触到了md5解密的网站，了解到原来是又一个密钥和明文对应的数据库，于是也想自己搞一个，仅仅是想想。
    3. 公元2019年，中国程序员winter又一次碰到使用别人的网站解密md5密码串，于是动手搞了一个小程序代码生成这些密码库，随晚了些，但总算动手做了。
    4. 但是发现自己的50G服务器搭建的数据库完全不够用,预算估计至少需要200G的磁盘，预算每年800元左右。
    5. 顾将代码分享出来给大家用有数据库足够大的可以放在服务器一直跑，如果不想自己搞的可以资助我一下
    
    
### 功能说明
    1. 生成0-9，不同位数组合数字
    2. 生成指定某些字符串，指定位数不同组合
### 使用说明
    1. 生成纯数字的话 moxi.core.demo.service.test.impl.PasswordServiceImpl.test
        此方法里有一个循环，i 循环值，表示从多少位数字到多少位数字，如下。表示生成从6未数字到9位数字
        ````
         for (int i = 6; i <= 9; i++) {
                    final int temp = i;
                    threadPoolTaskExecutor.execute(()->{
                        generateNumberRecursive("", temp, 1);
                    });
                }
        ````
    2. 根据自定义数字字符串生成不同组合
        moxi.core.demo.service.test.impl.PasswordServiceImpl.generateStr
        此方法会生成，通过配置变量 moxi.core.demo.service.test.impl.PasswordServiceImpl.key
        来控制有哪些值，通过循环的i来控制位数



### 资助方式
    支付宝：
    ![image](http://www.ichasem.tech/wp-content/uploads/2019/12/WechatIMG305.jpeg)
        
