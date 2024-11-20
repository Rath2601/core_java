### Note

* Java 9+ Modularization: From Java 9, JDK libraries are modularized. You can declare modules using **module-info.java** to specify dependencies, e.g., requires java.sql; for JDBC.
* **JDBC: Part of the java.sql module** for CRUD operations in modular applications.
* Unmodularized Applications: You can still use JDBC and **work without a module-info.java** file in non-modular applications (as before Java 9)
