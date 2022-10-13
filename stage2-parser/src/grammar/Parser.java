package grammar;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedHashSet;

import errors.SyntaxError;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class Parser {
  private Lexer lexer;
  private Token token;
  private LinkedHashSet<Integer> firstPrimaryExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstUnaryExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstExtendedMultiplicativeExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstMultiplicativeExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstExtendedAdditiveExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstAdditiveExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstRelationalExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstExtendedRelationalExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstEqualityExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstExtendedEqualityExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstConditionalTerm = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstExtendedConditionalTerm = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstConditionalExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstExtendedConditionalExpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstexpression = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstIfTrue = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstIfFalse = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstIfElseStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstIfStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstConditionalStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstRepetitivelStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstStructuredStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstElementList = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstElement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firsTextStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firsPenwidthStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firsColorStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstPendownStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstPenupStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstCircleStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstClearStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstDrawingStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstSetxyStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstSetyStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstSetxStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstLeftStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstRightStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstBackwardStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstForwardStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstMovementStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstAssigmentStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstIdentifierList = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstDeclarationStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstStatementSequence = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstProgram = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstSimpleStatement = new LinkedHashSet<Integer>();
  private LinkedHashSet<Integer> firstArcStatement = new LinkedHashSet<Integer>();
  
  public Parser (FileInputStream input) {
    lexer = new Lexer(input);
    
    firstPrimaryExpression.add(Tag.ID); firstPrimaryExpression.add(Tag.NUMBER);
    firstPrimaryExpression.add(Tag.TRUE); firstPrimaryExpression.add(Tag.FALSE);
    firstPrimaryExpression.add((int) '(');
    
    firstUnaryExpression.add((int) '-'); firstUnaryExpression.add((int) '!');
    firstUnaryExpression.addAll(firstPrimaryExpression);
    
    firstExtendedMultiplicativeExpression.add((int) '*'); firstExtendedMultiplicativeExpression.add((int) '/');
    firstExtendedMultiplicativeExpression.add(Tag.MOD);
    
    firstMultiplicativeExpression.containsAll(firstUnaryExpression);
    
    firstExtendedAdditiveExpression.add((int) '+'); firstExtendedAdditiveExpression.add((int) '-');
    
    firstAdditiveExpression.addAll(firstMultiplicativeExpression);

    firstExtendedRelationalExpression.add((int) '<'); firstExtendedRelationalExpression.add(Tag.LEQ);
    firstExtendedRelationalExpression.add((int) '>'); firstExtendedRelationalExpression.add(Tag.GEQ);
    
    firstRelationalExpression.addAll(firstAdditiveExpression);
    firstExtendedEqualityExpression.add((int) '='); firstExtendedEqualityExpression.add(Tag.NEQ);
    firstEqualityExpression.addAll(firstRelationalExpression);

    firstExtendedConditionalTerm.add(Tag.AND);
    firstConditionalTerm.addAll(firstEqualityExpression);
    firstExtendedConditionalExpression.add(Tag.OR);
    firstConditionalExpression.addAll(firstConditionalTerm);
    
    firstexpression.addAll(firstConditionalExpression);
    
    firstIfFalse.addAll(firstStatementSequence);
    firstIfTrue.addAll(firstStatementSequence);
    firstIfElseStatement.add(Tag.IFELSE);
    firstIfStatement.add(Tag.IF);

    firstConditionalStatement.addAll(firstIfStatement);firstConditionalStatement.addAll(firstIfElseStatement);
    
    firstRepetitivelStatement.add(Tag.REPEAT);
    
    firstStructuredStatement.addAll(firstRepetitivelStatement);firstStructuredStatement.addAll(firstConditionalStatement);
    
    firstElementList.add((int) ',');
    firstElement.add(Tag.STRING);firstElement.addAll(firstexpression);
    
    firsTextStatement.add(Tag.PRINT);
    
    firsPenwidthStatement.add(Tag.PENWIDTH);
    firsColorStatement.add(Tag.COLOR);
    firstPendownStatement.add(Tag.PENDOWN);
    firstPenupStatement.add(Tag.PENUP);
    firstCircleStatement.add(Tag.CIRCLE);
    firstClearStatement.add(Tag.CLEAR);
    
    firstDrawingStatement.addAll(firsColorStatement);firstDrawingStatement.addAll(firstClearStatement);
    firstDrawingStatement.addAll(firstCircleStatement);firstDrawingStatement.addAll(firstArcStatement);
    firstDrawingStatement.addAll(firstPenupStatement);firstDrawingStatement.addAll(firstPendownStatement);
    firstDrawingStatement.addAll(firsPenwidthStatement);
    
    firstSetxyStatement.add(Tag.SETXY);
    firstSetyStatement.add(Tag.SETY);
    firstSetxStatement.add(Tag.SETX);
    firstLeftStatement.add(Tag.LEFT);
    firstRightStatement.add(Tag.RIGHT);
    firstBackwardStatement.add(Tag.BACKWARD);
    firstForwardStatement.add(Tag.FORWARD);
    
    firstMovementStatement.addAll(firstForwardStatement);firstMovementStatement.addAll(firstBackwardStatement);
    firstMovementStatement.addAll(firstRightStatement);firstMovementStatement.addAll(firstLeftStatement);
    firstMovementStatement.addAll(firstSetxStatement);firstMovementStatement.addAll(firstSetyStatement);
    firstMovementStatement.addAll(firstSetxyStatement);
    firstMovementStatement.add(Tag.HOME);
    
    firstAssigmentStatement.add(Tag.ID);
    
    firstDeclarationStatement.add(Tag.VAR);
    
    firstSimpleStatement.addAll(firstDeclarationStatement);firstSimpleStatement.addAll(firstAssigmentStatement);
    firstSimpleStatement.addAll(firstMovementStatement);firstSimpleStatement.addAll(firstDrawingStatement);
    firstSimpleStatement.addAll(firstDeclarationStatement);
    firstStatement.addAll(firstSimpleStatement);firstStatement.addAll(firstStructuredStatement);
    
    firstStatementSequence.addAll(firstStatement);
    firstProgram.addAll(firstStatementSequence);
    firstArcStatement.add(Tag.ARC);
    firstIdentifierList.add((int) ',');
  }

  private void check(int tag) throws SyntaxError, IOException {
    if (token.getTag() == tag) {
      token = lexer.scan();
    } else {;
      throw new SyntaxError();
    }
  }

  public void analyze() throws SyntaxError, IOException {
    token = lexer.scan();
    if (firstProgram.contains(token.getTag())) {
      statementSequence();
    } else {
      throw new SyntaxError();
    }
    System.out.println("ACCEPTED");
    System.out.println("Parsed successfully");
  }
  
  private void primaryExpression() throws SyntaxError, IOException {
    if (firstPrimaryExpression.contains(token.getTag())) {
      switch(token.getTag()) {
      case Tag.ID : check(Tag.ID); break;
      case Tag.NUMBER : check(Tag.NUMBER); break;
      case Tag.TRUE : check(Tag.TRUE); break;
      case Tag.FALSE : check(Tag.FALSE); break;
      case ((int) '(') :
        check((int) '(');
        expression();
        check((int) ')');
        break;
      }
    } else {
      throw new SyntaxError();
    }
  }
  
  private void unaryExpression() throws SyntaxError, IOException {
    if (firstUnaryExpression.contains(token.getTag())) {
      switch(token.getTag()) {
      case ((int) '-') : check((int) '-'); unaryExpression(); break;
      case ((int) '!') : check((int) '!'); unaryExpression(); break;
      default			 : primaryExpression(); break;
      }
    } else {
      throw new SyntaxError();
    }
  }

  protected void simpleStatement() throws SyntaxError, IOException {
    if (firstSimpleStatement.contains(token.getTag())) {
      if (firstDeclarationStatement.contains(token.getTag()))   declarationStatement();
      else if(firstAssigmentStatement.contains(token.getTag())) assigmentStatement();
      else if(firstMovementStatement.contains(token.getTag()))  movementStatement();
      else if(firstDrawingStatement.contains(token.getTag()))   drawingStatement();
      else if(firsTextStatement.contains(token.getTag()))       textStatement();
    } else
      throw new SyntaxError();
  }

  protected void statement() throws SyntaxError, IOException {
    if (firstStatement.contains(token.getTag())) {
      if (firstSimpleStatement.contains(token.getTag()))
        simpleStatement();
      else if(firstStructuredStatement.contains(token.getTag()))
        structuredStatement();
    } else
      throw new SyntaxError();
  }

  protected void statementSequence() throws SyntaxError, IOException {
    if (firstStatementSequence.contains(token.getTag())) {
      statement();
      statementSequence();
    } else {} //noting
  }

  private void extendedMultiplicativeExpression() throws SyntaxError, IOException {
    if (firstExtendedMultiplicativeExpression.contains(token.getTag())) {
      switch(token.getTag()) {
      case ((int) '*') : 
        check((int) '*');
        unaryExpression();
        extendedMultiplicativeExpression();
        break;
      case ((int) '/') : 
        check((int) '/');
        unaryExpression();
        extendedMultiplicativeExpression();
        break;
      case Tag.MOD : 
        check(Tag.MOD);
        unaryExpression();
        extendedMultiplicativeExpression();
        break;
      }
    } else {} //nothing
  }

  protected void multiplicativeExpression() throws SyntaxError, IOException {
    if (firstMultiplicativeExpression.contains(token.getTag())) {
      unaryExpression();
      extendedMultiplicativeExpression();
    }
  }

  protected void extendedAdditiveExpression() throws SyntaxError, IOException {
    if (firstExtendedAdditiveExpression.contains(token.getTag())) {
      switch(token.getTag()) {
      case ((int) '+') : 
        check((int) '+');
        multiplicativeExpression();
        extendedAdditiveExpression();
        break;
      case ((int) '-') : 
        check((int) '-');
        multiplicativeExpression();
        extendedAdditiveExpression();
        break;
      }
    } else {} // nothing
  }

  protected void additiveExpression() throws SyntaxError, IOException {
    if (firstAdditiveExpression.contains(token.getTag())) {
      multiplicativeExpression();
      extendedAdditiveExpression();
    }
  }

    protected void relationalExpression() throws SyntaxError, IOException {
    if (firstRelationalExpression.contains(token.getTag())) {
      additiveExpression();
      extendedRelationalExpression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void extendedRelationalExpression() throws SyntaxError, IOException {
    if (firstExtendedRelationalExpression.contains(token.getTag())) {
      switch(token.getTag()) {
      case ((int) '<') : 
        check((int) '<');
        additiveExpression();
        extendedRelationalExpression();
        break;
      case Tag.LEQ :
        check(Tag.LEQ);
        additiveExpression();
        extendedRelationalExpression(); 
        break;
      case ((int) '>') : 
        check((int) '>');
        additiveExpression();
        extendedRelationalExpression();
        break;
      case Tag.GEQ :
        check(Tag.GEQ);
        additiveExpression();
        extendedRelationalExpression();
        break;
      }
    } else {} // nothing
  }
  protected void equalityExpression() throws SyntaxError, IOException {
    if (firstEqualityExpression.contains(token.getTag())) {
      relationalExpression();
      extendedEqualityExpression();
    } else {
      throw new SyntaxError();
    }
  }
  protected void extendedEqualityExpression() throws SyntaxError, IOException {
    if (firstExtendedEqualityExpression.contains(token.getTag())) {
      switch(token.getTag()) {
      case ((int) '=') : 
        check((int) '=');
        relationalExpression();
        extendedEqualityExpression();
        break;
      case (Tag.NEQ) :
        check(Tag.NEQ);
        relationalExpression();
        extendedEqualityExpression();
        break;
      }
    } else {} // nothing
  }
  
  protected void conditionalTerm() throws SyntaxError, IOException {
    if (firstConditionalTerm.contains(token.getTag())) {
      equalityExpression();
      extendedConditionalTerm();
    } else {
      
    }
  }
  protected void extendedConditionalTerm() throws SyntaxError, IOException {
    if (firstExtendedConditionalTerm.contains(token.getTag())) {
      switch(token.getTag()) {
      case (Tag.AND) : 
        check(Tag.AND);
        equalityExpression();
        extendedConditionalTerm();
        break;
      }
    } else {} //nothing
  }

  protected void conditionalExpression() throws SyntaxError, IOException {
    if (firstConditionalExpression.contains(token.getTag())) {
      conditionalTerm();
      extendedConditionalExpression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void extendedConditionalExpression() throws SyntaxError, IOException {
    if (firstExtendedConditionalExpression.contains(token.getTag())) {
      switch(token.getTag()) {
      case (Tag.OR) : 
        check(Tag.OR);
        conditionalTerm();
        extendedConditionalExpression();
        break;
      }
    } else {} // nothing
  }

  protected void expression() throws SyntaxError, IOException {
    if (firstexpression.contains(token.getTag())) {
      conditionalExpression();
    }
    else {
      System.out.println("Syntax Error: " + token.getTag());
      throw new SyntaxError();
    }
  }

  protected void ifTrue() throws SyntaxError, IOException {
    if (firstIfTrue.contains(token.getTag()))
      statementSequence();
  }
  protected void ifFalse() throws SyntaxError, IOException {
    if (firstIfFalse.contains(token.getTag()))
      statementSequence();
  }

  protected void ifElseStatement() throws SyntaxError, IOException {
    if (firstIfElseStatement.contains(token.getTag())) {
      check(Tag.IFELSE);
      expression();
      check((int) '[');  
      ifTrue();
      check((int) ']');  
      check((int) '[');  
      ifFalse();
      check((int) ']');  
    } else {
      throw new SyntaxError();
    }
  }

  protected void ifStatement() throws SyntaxError, IOException {
    if (firstIfStatement.contains(token.getTag())) {
      check(Tag.IF);
      expression();
      check((int) '[');  
      ifTrue();
      check((int) ']');  
      
    } else {
      throw new SyntaxError();
    }
  }

  protected void conditionalStatement() throws SyntaxError, IOException {
    if (firstConditionalStatement.contains(token.getTag())) {
      if(firstIfStatement.contains(token.getTag()))
        ifStatement();
      else
        ifElseStatement();
    } else {
      throw new SyntaxError();
    }
  }

  protected void repetitivelStatement() throws SyntaxError, IOException {
    if (firstRepetitivelStatement.contains(token.getTag())) {
      check(Tag.REPEAT);
      expression();
      check((int) '[');  
      statementSequence();
      check((int) ']');
    } else {
      throw new SyntaxError();
    }
  }

  protected void structuredStatement() throws SyntaxError, IOException {
    if (firstStructuredStatement.contains(token.getTag())) {
      if (firstRepetitivelStatement.contains(token.getTag())) {
        repetitivelStatement();
      } else {
        conditionalStatement();
      }
    } else {
      throw new SyntaxError();
    }
  }
  protected void elementList() throws SyntaxError, IOException {
    if (firstElementList.contains(token.getTag())) {
      check((int) ',');  
      element();
      elementList();
    } else {} // nothing
  }
  protected void element() throws SyntaxError, IOException {
    if (firstElement.contains(token.getTag())) {
      if(token.getTag()==Tag.STRING) {
        check(Tag.STRING); 
      } else {
        expression();
      }
    } else {
      throw new SyntaxError();
    }
  }
  
  protected void textStatement() throws SyntaxError, IOException {
    if (firsTextStatement.contains(token.getTag())) {
      check(Tag.PRINT);
      check((int) '[');  
      element();
      elementList();
      check((int) ']');
    } else {
      throw new SyntaxError();
    }
  }
  protected void penwidthStatement() throws SyntaxError, IOException {
    if (firsPenwidthStatement.contains(token.getTag())) {
      check(Tag.PENWIDTH); //PENWIDTH =  "Penwidth" on lexer file 
      expression();
    } else {
      throw new SyntaxError();
    }
  }
  protected void colorStatement() throws SyntaxError, IOException {
    if (firsColorStatement.contains(token.getTag())) {
      check(Tag.COLOR);
      expression();
      expression();
      expression();
    } else {
      throw new SyntaxError();
    }
  }
  protected void pendownStatement() throws SyntaxError, IOException {
    if (firstPendownStatement.contains(token.getTag())) {
      check(Tag.PENDOWN);
    } else {
      throw new SyntaxError();
    }
  }
  
  protected void penupStatement() throws SyntaxError, IOException {
    if (Tag.PENUP==token.getTag()) {
      check(Tag.PENUP);
    } else {
      throw new SyntaxError();
    }
  }
  
  protected void arcStatement() throws SyntaxError, IOException {
    if (firstArcStatement.contains(token.getTag())) {
      check(Tag.ARC);
      expression();
    } else {
      throw new SyntaxError();
    }
  }
  protected void circleStatement() throws SyntaxError, IOException {
    if (firstCircleStatement.contains(token.getTag())) {
      check(Tag.CIRCLE);
      expression();
    } else {
      throw new SyntaxError();
    }
  }
  protected void clearStatement() throws SyntaxError, IOException {
    if (firstClearStatement.contains(token.getTag())) {
      check(Tag.CLEAR);
      
    } else {
      throw new SyntaxError();
    }
  }
  protected void drawingStatement() throws SyntaxError, IOException {
    if (firstDrawingStatement.contains(token.getTag())) {
      switch(token.getTag()) {
      case Tag.CLEAR : 
        clearStatement();
        break;
      case Tag.CIRCLE : 
        circleStatement();
        break;
      case Tag.ARC : 
        arcStatement();
        break;
      case Tag.PENUP : 
        check(Tag.PENUP);
        penupStatement();
        break;
      case Tag.PENDOWN: 
        pendownStatement();
        break;
      case Tag.COLOR: 
        colorStatement();
        break;
      case Tag.PENWIDTH: 
        colorStatement();
        break;
      }	
    } else {
      throw new SyntaxError();
    }
  }

  protected void setxyStatement() throws SyntaxError, IOException {
    if (firstSetxyStatement.contains(token.getTag())) {
      check(Tag.SETXY); 
      expression();
      expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void setyStatement() throws SyntaxError, IOException {
    if (firstSetyStatement.contains(token.getTag())) {
      check(Tag.SETY);
      expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void setxStatement() throws SyntaxError, IOException {
    if (firstSetxStatement.contains(token.getTag())) {
      check(Tag.SETX);
      expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void leftStatement() throws SyntaxError, IOException {
    if (firstLeftStatement.contains(token.getTag())) {
      check(Tag.LEFT);
      expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void rightStatement() throws SyntaxError, IOException {
    if (firstRightStatement.contains(token.getTag())) {
      check(Tag.RIGHT);
      expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void backwardStatement() throws SyntaxError, IOException {
    if (firstBackwardStatement.contains(token.getTag())) {
        check(Tag.BACKWARD);
        expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void forwardStatement() throws SyntaxError, IOException {
    if (firstForwardStatement.contains(token.getTag())) {
        check(Tag.FORWARD);
        expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void movementStatement() throws SyntaxError, IOException {
    if (firstMovementStatement.contains(token.getTag())) {
      switch(token.getTag()) {
        case Tag.FORWARD : 
          forwardStatement();
          break;
        case Tag.BACKWARD : 
          backwardStatement();
          break;
        case Tag.RIGHT : 
          rightStatement();
          break;
        case Tag.LEFT : 
          leftStatement();
          break;
        case Tag.SETX: 
          setxStatement();
          break;
        case Tag.SETY: 
          setyStatement();
          break;
        case Tag.SETXY: 
          setxyStatement();
          break;
        case Tag.HOME: 
          check(Tag.HOME);
          break;
      }	
    } else {
      throw new SyntaxError();
    }
  }

  protected void assigmentStatement() throws SyntaxError, IOException {
    if (firstAssigmentStatement.contains(token.getTag())) {
      check(Tag.ID);
      check(Tag.ASSIGNMENT);
      expression();
    } else {
      throw new SyntaxError();
    }
  }

  protected void identifierList() throws SyntaxError, IOException {
    if (firstIdentifierList.contains(token.getTag())) {
      check((int) ',');  
      check(Tag.ID);  
      identifierList();
  
    } else {} //nothing
  }

  protected void declarationStatement() throws SyntaxError, IOException {
    if (firstDeclarationStatement.contains(token.getTag())) {
      check(Tag.VAR);  
      check(Tag.ID);  
      identifierList();
  
    } else {
      throw new SyntaxError();
    }
  }
}
