#  MyBatisLogFormatter
<!-- Plugin description -->
MyBatisLogFormatter，用于提取MyBatis日志、将参数填充到SQL中以及压缩和美化SQL。
在控制台中，你只需要选中MyBatis日志如下，
```
==>  Preparing: SELECT ID,phone,avatar,nickname,active,use_work_space_id,created_at,created_by,updated_at,updated_by FROM sys_user WHERE ID=?
==> Parameters: 1837781314710069250(Long)
```
然后右键选择MyBatisLogFormatter即可完成SQL格式化并复制到剪贴板，或者复制这段日志到右侧工具窗口进行格式化
如果你有疑问或其他想法，可以在[Issues](https://github.com/Alan-Echo/MyBatisLogFormatter/issues)进行反馈。

The MyBatisLogFormatter is designed to help extract MyBatis logs, fill SQL parameters, and compress or beautify SQL statements.
In the console, simply select the MyBatis log as shown below:
```
==>  Preparing: SELECT ID,phone,avatar,nickname,active,use_work_space_id,created_at,created_by,updated_at,updated_by FROM sys_user WHERE ID=?
==> Parameters: 1837781314710069250(Long)
```
Then, right-click and select MyBatisLogFormatter to complete SQL formatting and copy it to the clipboard. Alternatively, you can paste the log into the tool window on the right to format it.

If you have questions or suggestions, feel free to provide feedback in the [Issues](https://github.com/Alan-Echo/MyBatisLogFormatter/issues) section.
<!-- Plugin description end -->