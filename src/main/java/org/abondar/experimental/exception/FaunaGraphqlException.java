package org.abondar.experimental.exception;

public class FaunaGraphqlException extends RuntimeException{
   public FaunaGraphqlException(String msg){
      super(msg);
   }
}
