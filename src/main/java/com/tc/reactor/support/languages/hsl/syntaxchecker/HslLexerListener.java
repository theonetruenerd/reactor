// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/hsl/syntaxchecker/HslLexer.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.hsl.syntaxchecker;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HslLexerParser}.
 */
public interface HslLexerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#hslFile}.
	 * @param ctx the parse tree
	 */
	void enterHslFile(HslLexerParser.HslFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#hslFile}.
	 * @param ctx the parse tree
	 */
	void exitHslFile(HslLexerParser.HslFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(HslLexerParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(HslLexerParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#controlLine}.
	 * @param ctx the parse tree
	 */
	void enterControlLine(HslLexerParser.ControlLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#controlLine}.
	 * @param ctx the parse tree
	 */
	void exitControlLine(HslLexerParser.ControlLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HslLexerParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HslLexerParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(HslLexerParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(HslLexerParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(HslLexerParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(HslLexerParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(HslLexerParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(HslLexerParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#flowControlStatement}.
	 * @param ctx the parse tree
	 */
	void enterFlowControlStatement(HslLexerParser.FlowControlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#flowControlStatement}.
	 * @param ctx the parse tree
	 */
	void exitFlowControlStatement(HslLexerParser.FlowControlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterControlStatement(HslLexerParser.ControlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitControlStatement(HslLexerParser.ControlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(HslLexerParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(HslLexerParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#declSpecifiers}.
	 * @param ctx the parse tree
	 */
	void enterDeclSpecifiers(HslLexerParser.DeclSpecifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#declSpecifiers}.
	 * @param ctx the parse tree
	 */
	void exitDeclSpecifiers(HslLexerParser.DeclSpecifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(HslLexerParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(HslLexerParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceDefinition(HslLexerParser.NamespaceDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceDefinition(HslLexerParser.NamespaceDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterStructure(HslLexerParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitStructure(HslLexerParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(HslLexerParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(HslLexerParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#tagDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterTagDeclarationList(HslLexerParser.TagDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#tagDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitTagDeclarationList(HslLexerParser.TagDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#tagDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTagDeclaration(HslLexerParser.TagDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#tagDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTagDeclaration(HslLexerParser.TagDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(HslLexerParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(HslLexerParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#storage}.
	 * @param ctx the parse tree
	 */
	void enterStorage(HslLexerParser.StorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#storage}.
	 * @param ctx the parse tree
	 */
	void exitStorage(HslLexerParser.StorageContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#formalList}.
	 * @param ctx the parse tree
	 */
	void enterFormalList(HslLexerParser.FormalListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#formalList}.
	 * @param ctx the parse tree
	 */
	void exitFormalList(HslLexerParser.FormalListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(HslLexerParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(HslLexerParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#errorHandler}.
	 * @param ctx the parse tree
	 */
	void enterErrorHandler(HslLexerParser.ErrorHandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#errorHandler}.
	 * @param ctx the parse tree
	 */
	void exitErrorHandler(HslLexerParser.ErrorHandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(HslLexerParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(HslLexerParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#sequenceExpression}.
	 * @param ctx the parse tree
	 */
	void enterSequenceExpression(HslLexerParser.SequenceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#sequenceExpression}.
	 * @param ctx the parse tree
	 */
	void exitSequenceExpression(HslLexerParser.SequenceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(HslLexerParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(HslLexerParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#deviceExpression}.
	 * @param ctx the parse tree
	 */
	void enterDeviceExpression(HslLexerParser.DeviceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#deviceExpression}.
	 * @param ctx the parse tree
	 */
	void exitDeviceExpression(HslLexerParser.DeviceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#resourceExpression}.
	 * @param ctx the parse tree
	 */
	void enterResourceExpression(HslLexerParser.ResourceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#resourceExpression}.
	 * @param ctx the parse tree
	 */
	void exitResourceExpression(HslLexerParser.ResourceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#dialogExpression}.
	 * @param ctx the parse tree
	 */
	void enterDialogExpression(HslLexerParser.DialogExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#dialogExpression}.
	 * @param ctx the parse tree
	 */
	void exitDialogExpression(HslLexerParser.DialogExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#objectExpression}.
	 * @param ctx the parse tree
	 */
	void enterObjectExpression(HslLexerParser.ObjectExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#objectExpression}.
	 * @param ctx the parse tree
	 */
	void exitObjectExpression(HslLexerParser.ObjectExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(HslLexerParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(HslLexerParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#timerExpression}.
	 * @param ctx the parse tree
	 */
	void enterTimerExpression(HslLexerParser.TimerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#timerExpression}.
	 * @param ctx the parse tree
	 */
	void exitTimerExpression(HslLexerParser.TimerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#eventExpression}.
	 * @param ctx the parse tree
	 */
	void enterEventExpression(HslLexerParser.EventExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#eventExpression}.
	 * @param ctx the parse tree
	 */
	void exitEventExpression(HslLexerParser.EventExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#fileExpression}.
	 * @param ctx the parse tree
	 */
	void enterFileExpression(HslLexerParser.FileExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#fileExpression}.
	 * @param ctx the parse tree
	 */
	void exitFileExpression(HslLexerParser.FileExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(HslLexerParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(HslLexerParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#leftExpr}.
	 * @param ctx the parse tree
	 */
	void enterLeftExpr(HslLexerParser.LeftExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#leftExpr}.
	 * @param ctx the parse tree
	 */
	void exitLeftExpr(HslLexerParser.LeftExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#rightExpr}.
	 * @param ctx the parse tree
	 */
	void enterRightExpr(HslLexerParser.RightExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#rightExpr}.
	 * @param ctx the parse tree
	 */
	void exitRightExpr(HslLexerParser.RightExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(HslLexerParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(HslLexerParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(HslLexerParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(HslLexerParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(HslLexerParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(HslLexerParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#optInitExpression}.
	 * @param ctx the parse tree
	 */
	void enterOptInitExpression(HslLexerParser.OptInitExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#optInitExpression}.
	 * @param ctx the parse tree
	 */
	void exitOptInitExpression(HslLexerParser.OptInitExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#optExpression}.
	 * @param ctx the parse tree
	 */
	void enterOptExpression(HslLexerParser.OptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#optExpression}.
	 * @param ctx the parse tree
	 */
	void exitOptExpression(HslLexerParser.OptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#optForExpression}.
	 * @param ctx the parse tree
	 */
	void enterOptForExpression(HslLexerParser.OptForExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#optForExpression}.
	 * @param ctx the parse tree
	 */
	void exitOptForExpression(HslLexerParser.OptForExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#functionReference}.
	 * @param ctx the parse tree
	 */
	void enterFunctionReference(HslLexerParser.FunctionReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#functionReference}.
	 * @param ctx the parse tree
	 */
	void exitFunctionReference(HslLexerParser.FunctionReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#bindings}.
	 * @param ctx the parse tree
	 */
	void enterBindings(HslLexerParser.BindingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#bindings}.
	 * @param ctx the parse tree
	 */
	void exitBindings(HslLexerParser.BindingsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#sequenceId}.
	 * @param ctx the parse tree
	 */
	void enterSequenceId(HslLexerParser.SequenceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#sequenceId}.
	 * @param ctx the parse tree
	 */
	void exitSequenceId(HslLexerParser.SequenceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#deviceId}.
	 * @param ctx the parse tree
	 */
	void enterDeviceId(HslLexerParser.DeviceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#deviceId}.
	 * @param ctx the parse tree
	 */
	void exitDeviceId(HslLexerParser.DeviceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#resourceId}.
	 * @param ctx the parse tree
	 */
	void enterResourceId(HslLexerParser.ResourceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#resourceId}.
	 * @param ctx the parse tree
	 */
	void exitResourceId(HslLexerParser.ResourceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#dialogId}.
	 * @param ctx the parse tree
	 */
	void enterDialogId(HslLexerParser.DialogIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#dialogId}.
	 * @param ctx the parse tree
	 */
	void exitDialogId(HslLexerParser.DialogIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#objectId}.
	 * @param ctx the parse tree
	 */
	void enterObjectId(HslLexerParser.ObjectIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#objectId}.
	 * @param ctx the parse tree
	 */
	void exitObjectId(HslLexerParser.ObjectIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#arrayId}.
	 * @param ctx the parse tree
	 */
	void enterArrayId(HslLexerParser.ArrayIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#arrayId}.
	 * @param ctx the parse tree
	 */
	void exitArrayId(HslLexerParser.ArrayIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#timerId}.
	 * @param ctx the parse tree
	 */
	void enterTimerId(HslLexerParser.TimerIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#timerId}.
	 * @param ctx the parse tree
	 */
	void exitTimerId(HslLexerParser.TimerIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#eventId}.
	 * @param ctx the parse tree
	 */
	void enterEventId(HslLexerParser.EventIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#eventId}.
	 * @param ctx the parse tree
	 */
	void exitEventId(HslLexerParser.EventIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#fileId}.
	 * @param ctx the parse tree
	 */
	void enterFileId(HslLexerParser.FileIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#fileId}.
	 * @param ctx the parse tree
	 */
	void exitFileId(HslLexerParser.FileIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#stringId}.
	 * @param ctx the parse tree
	 */
	void enterStringId(HslLexerParser.StringIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#stringId}.
	 * @param ctx the parse tree
	 */
	void exitStringId(HslLexerParser.StringIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#funcId}.
	 * @param ctx the parse tree
	 */
	void enterFuncId(HslLexerParser.FuncIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#funcId}.
	 * @param ctx the parse tree
	 */
	void exitFuncId(HslLexerParser.FuncIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#protoId}.
	 * @param ctx the parse tree
	 */
	void enterProtoId(HslLexerParser.ProtoIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#protoId}.
	 * @param ctx the parse tree
	 */
	void exitProtoId(HslLexerParser.ProtoIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#typeId}.
	 * @param ctx the parse tree
	 */
	void enterTypeId(HslLexerParser.TypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#typeId}.
	 * @param ctx the parse tree
	 */
	void exitTypeId(HslLexerParser.TypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#namespaceId}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceId(HslLexerParser.NamespaceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#namespaceId}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceId(HslLexerParser.NamespaceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(HslLexerParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(HslLexerParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#cString}.
	 * @param ctx the parse tree
	 */
	void enterCString(HslLexerParser.CStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#cString}.
	 * @param ctx the parse tree
	 */
	void exitCString(HslLexerParser.CStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(HslLexerParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(HslLexerParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclSpecifier(HslLexerParser.DeclSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclSpecifier(HslLexerParser.DeclSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslLexerParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(HslLexerParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslLexerParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(HslLexerParser.TypeContext ctx);
}