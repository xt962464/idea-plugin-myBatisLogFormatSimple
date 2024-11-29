#  MyBatisLogFormatter
<!-- Plugin description -->
MyBatisLogFormatter，用于提取MyBatis日志并将参数填充到SQL中、压缩、美化SQL、一键打开Query Console。
在控制台中，你只需要选中MyBatis日志如下，
```
==>  Preparing: SELECT ID,phone,avatar,nickname,active,use_work_space_id,created_at,created_by,updated_at,updated_by FROM sys_user WHERE ID=?
==> Parameters: 1837781314710069250(Long)
```
然后点击鼠标右键弹出菜单选项，选择MyBatisLogFormatter，即可完成SQL格式化并复制到剪贴板，或者复制这段日志到右侧工具窗口进行格式化；选择MyBatisLogFormatter To SQL QueryConsole选项则可以实现MyBatisLog日志的sql提取、格式化并打开SQL Query Console
如果你有疑问或其他想法，可以在[Issues](https://github.com/Alan-Echo/MyBatisLogFormatter/issues)进行反馈。

MyBatisLogFormatter is a tool designed to extract MyBatis logs, fill in parameters into SQL statements, compress, beautify the SQL, and open the Query Console with one click.
In the console, simply select the MyBatis log like the example below:
```
==>  Preparing: SELECT ID,phone,avatar,nickname,active,use_work_space_id,created_at,created_by,updated_at,updated_by FROM sys_user WHERE ID=?
==> Parameters: 1837781314710069250(Long)
```
Then, right-click to bring up the context menu and select MyBatisLogFormatter to format the SQL and copy it to the clipboard. Alternatively, you can copy the log to the tool window on the right for formatting.
By selecting the MyBatisLogFormatter To SQL QueryConsole option, you can extract and format SQL from MyBatis logs and open the SQL Query Console directly.

If you have questions or suggestions, feel free to provide feedback in the [Issues](https://github.com/Alan-Echo/MyBatisLogFormatter/issues) section.
<!-- Plugin description end -->