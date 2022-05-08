package jpabasic.dialect;

import org.hibernate.dialect.MySQL8Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MyMySQLDialect extends MySQL8Dialect {

  public MyMySQLDialect() {
    registerFunction( "group_concat", new StandardSQLFunction( "group_concat", StandardBasicTypes.STRING ) );
  }
}
