### 网络编程

#### 七层网络协议

+ intro

  <img src="../../images/network-intro.png">

#### 相关协议

+ intro

  <img src="../../images/Tcp-Udp-intro.png">

#### IP地址和端口号

+ intro

  <img src="../../images/ip-port-intro.png">

#### 基于tcp协议的编程模型

+ intro

  <img src="../../images/tcp-model-intro.png">

#### 基于tcp协议模型的框架实现

+ intro

  <img src="../../images/tcp-model-methods.png">

+ Server

  ```java
  public class ServerStringTest {
  
      public static void main(String[] args) {
  
          ServerSocket ss = null;
          Socket s = null;
  
          try {
              // 1.创建ServerSocket类型的对象并提供端口号
              ss = new ServerSocket(8888);
              // 2.等待客户端连接请求，调用accept方法
              System.out.println("client is waiting...");
              s = ss.accept();
              System.out.println("client is connected!");
              // 3.使用输入输出流进行通信
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              // 4.关闭socket并释放有关的资源
              if(null != s) {
                  try {
                      s.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              if(null != ss) {
                  try {
                      ss.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
  
      }
  }
  
  ```

+ client

  ```java
  public class ClientStringTest {
  
      public static void main(String[] args) {
          Socket s = null;
  
          try {
              // 1. 创建Socket类型对象并提供服务器的主机名和端口号
              s = new Socket("127.0.0.1",8888);
              // 2. 使用输入输出流进行通信
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              // 3. 关闭Socket释放资源
              if(null != s) {
                  try {
                      s.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
  
      }
  }
  
  ```

  

#### 客户端向服务器发送数据的实现

+ Server

  ```java
  public class ServerStringTest {
  
      public static void main(String[] args) {
  
          ServerSocket ss = null;
          Socket s = null;
          BufferedReader br = null;
          try {
              // 1.创建ServerSocket类型的对象并提供端口号
              ss = new ServerSocket(8888);
              // 2.等待客户端连接请求，调用accept方法
              System.out.println("client is waiting...");
              s = ss.accept();
              System.out.println("client is connected!");
              // 3.使用输入输出流进行通信
              br = new BufferedReader(new InputStreamReader(s.getInputStream()));
              String s1 = br.readLine();
              System.out.println("the info of client is : "+ s1);
          } catch (IOException e) {
              e.printStackTrace();
          } finally {
              // 4.关闭socket并释放有关的资源
              if(null != s) {
                  try {
                      s.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              if(null != ss) {
                  try {
                      ss.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              if(null != br) {
                  try {
                      br.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
          }
  
      }
  }
  
  ```

+ client

  ```java
  public class ClientStringTest {
  
      public static void main(String[] args) {
          Socket s = null;
          PrintStream ps =null;
          try {
              // 1. 创建Socket类型对象并提供服务器的主机名和端口号
              s = new Socket("127.0.0.1",8888);
              // 2. 使用输入输出流进行通信
              Thread.sleep(5000);
              ps = new PrintStream(s.getOutputStream());
              ps.println("hello");
              System.out.println("info of client sends successfully");
          } catch (IOException e) {
              e.printStackTrace();
          } catch (InterruptedException e) {
              e.printStackTrace();
          } finally {
              // 3. 关闭Socket释放资源
              if(null != s) {
                  try {
                      s.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
              if(null != ps) {
                  ps.close();
              }
          }
  
      }
  }
  
  ```

  

#### 服务器向客户端回发数据的实现

+ server

  ```java
  public static void main(String[] args) {
  
          ServerSocket ss = null;
          Socket s = null;
          BufferedReader br = null;
          PrintStream ps = null;
          try {
              // 1.创建ServerSocket类型的对象并提供端口号
              ss = new ServerSocket(8888);
              // 2.等待客户端连接请求，调用accept方法
              System.out.println("client is waiting...");
              s = ss.accept();
              System.out.println("client is connected!");
              // 3.使用输入输出流进行通信
              br = new BufferedReader(new InputStreamReader(s.getInputStream()));
              String s1 = br.readLine();
              System.out.println("the info of client is : "+ s1);
              // 服务器返回信息
              ps = new PrintStream(s.getOutputStream());
              ps.println("message received!");
              System.out.println("server send message successdully!");
          }
      ...
  }
  ```

+ client

  ```java
  public static void main(String[] args) {
  
          Socket s = null;
          PrintStream ps =null;
          Scanner sc = null;
          BufferedReader br = null;
          try {
              // 1. 创建Socket类型对象并提供服务器的主机名和端口号
              s = new Socket("127.0.0.1",8888);
              // 2. 使用输入输出流进行通信
              sc = new Scanner(System.in);
              System.out.println("please input the message you want to send: ");
              String str = sc.nextLine();
              ps = new PrintStream(s.getOutputStream());
              // ps.println("hello");
              ps.println(str);
              System.out.println("info of client sends successfully");
              br = new BufferedReader(new InputStreamReader(s.getInputStream()));
              String s1 = br.readLine();
              System.out.println("the info server send is :"+s1);
          }
      ...
  }
  ```

  

#### 客户端和服务器不断通信的实现

+ server

  ```java
  public static void main(String[] args) {
  
          ServerSocket ss = null;
          Socket s = null;
          BufferedReader br = null;
          PrintStream ps = null;
          try {
              // 1.创建ServerSocket类型的对象并提供端口号
              ss = new ServerSocket(8888);
              // 2.等待客户端连接请求，调用accept方法
              System.out.println("client is waiting...");
              s = ss.accept();
              System.out.println("client is connected!");
              // 3.使用输入输出流进行通信
              br = new BufferedReader(new InputStreamReader(s.getInputStream()));
              ps = new PrintStream(s.getOutputStream());
              while (true) {
                  String s1 = br.readLine();
                  System.out.println("the info of client is : " + s1);
                  // 服务器返回信息
                  ps.println("message received!");
                  System.out.println("server send message successdully!");
              }
          }
      ...
  }
  ```

+ client

  ```java
  public static void main(String[] args) {
  
          Socket s = null;
          PrintStream ps =null;
          Scanner sc = null;
          BufferedReader br = null;
          try {
              // 1. 创建Socket类型对象并提供服务器的主机名和端口号
              s = new Socket("127.0.0.1",8888);
              System.out.println("succeed to connnect to server!");
              // 2. 使用输入输出流进行通信
              sc = new Scanner(System.in);
              ps = new PrintStream(s.getOutputStream());
              br = new BufferedReader(new InputStreamReader(s.getInputStream()));
              while(true) {
                  System.out.println("please input the message you want to send: ");
                  String str = sc.nextLine();
                  if("bye".equalsIgnoreCase(str)) {
                      System.out.println("chat is over");
                      break;
                  }
                  // ps.println("hello");
                  ps.println(str);
                  System.out.println("info of client sends successfully");
                  String s1 = br.readLine();
                  System.out.println("the info server send is :" + s1);
              }
          }
      ...
  }
  ```

  

#### 服务器采用多线程机制的实现

#### 基于udp协议的编程模型

#### 发送方发送字符串的实现

#### 接收方会发消息的实现

#### URL类的概念和使用