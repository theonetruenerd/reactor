// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/hsl/syntaxchecker/Hsl.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.hsl.syntaxchecker;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HslParser}.
 */
public interface HslListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HslParser#hslFile}.
	 * @param ctx the parse tree
	 */
	void enterHslFile(HslParser.HslFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#hslFile}.
	 * @param ctx the parse tree
	 */
	void exitHslFile(HslParser.HslFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(HslParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(HslParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#controlLine}.
	 * @param ctx the parse tree
	 */
	void enterControlLine(HslParser.ControlLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#controlLine}.
	 * @param ctx the parse tree
	 */
	void exitControlLine(HslParser.ControlLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HslParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HslParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSimpleStatement(HslParser.SimpleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#simpleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSimpleStatement(HslParser.SimpleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void enterCompoundStatement(HslParser.CompoundStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#compoundStatement}.
	 * @param ctx the parse tree
	 */
	void exitCompoundStatement(HslParser.CompoundStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(HslParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(HslParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#flowControlStatement}.
	 * @param ctx the parse tree
	 */
	void enterFlowControlStatement(HslParser.FlowControlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#flowControlStatement}.
	 * @param ctx the parse tree
	 */
	void exitFlowControlStatement(HslParser.FlowControlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void enterControlStatement(HslParser.ControlStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#controlStatement}.
	 * @param ctx the parse tree
	 */
	void exitControlStatement(HslParser.ControlStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(HslParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(HslParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#formalList}.
	 * @param ctx the parse tree
	 */
	void enterFormalList(HslParser.FormalListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#formalList}.
	 * @param ctx the parse tree
	 */
	void exitFormalList(HslParser.FormalListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(HslParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(HslParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#declSpecifiers}.
	 * @param ctx the parse tree
	 */
	void enterDeclSpecifiers(HslParser.DeclSpecifiersContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#declSpecifiers}.
	 * @param ctx the parse tree
	 */
	void exitDeclSpecifiers(HslParser.DeclSpecifiersContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceDefinition(HslParser.NamespaceDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceDefinition(HslParser.NamespaceDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#structure}.
	 * @param ctx the parse tree
	 */
	void enterStructure(HslParser.StructureContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#structure}.
	 * @param ctx the parse tree
	 */
	void exitStructure(HslParser.StructureContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(HslParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(HslParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#tagDeclarationList}.
	 * @param ctx the parse tree
	 */
	void enterTagDeclarationList(HslParser.TagDeclarationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#tagDeclarationList}.
	 * @param ctx the parse tree
	 */
	void exitTagDeclarationList(HslParser.TagDeclarationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#tagDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTagDeclaration(HslParser.TagDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#tagDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTagDeclaration(HslParser.TagDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(HslParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(HslParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#storage}.
	 * @param ctx the parse tree
	 */
	void enterStorage(HslParser.StorageContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#storage}.
	 * @param ctx the parse tree
	 */
	void exitStorage(HslParser.StorageContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(HslParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(HslParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#errorHandler}.
	 * @param ctx the parse tree
	 */
	void enterErrorHandler(HslParser.ErrorHandlerContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#errorHandler}.
	 * @param ctx the parse tree
	 */
	void exitErrorHandler(HslParser.ErrorHandlerContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentExpression(HslParser.AssignmentExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#assignmentExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentExpression(HslParser.AssignmentExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#sequenceExpression}.
	 * @param ctx the parse tree
	 */
	void enterSequenceExpression(HslParser.SequenceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#sequenceExpression}.
	 * @param ctx the parse tree
	 */
	void exitSequenceExpression(HslParser.SequenceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void enterStringExpression(HslParser.StringExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#stringExpression}.
	 * @param ctx the parse tree
	 */
	void exitStringExpression(HslParser.StringExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#deviceExpression}.
	 * @param ctx the parse tree
	 */
	void enterDeviceExpression(HslParser.DeviceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#deviceExpression}.
	 * @param ctx the parse tree
	 */
	void exitDeviceExpression(HslParser.DeviceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#resourceExpression}.
	 * @param ctx the parse tree
	 */
	void enterResourceExpression(HslParser.ResourceExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#resourceExpression}.
	 * @param ctx the parse tree
	 */
	void exitResourceExpression(HslParser.ResourceExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#dialogExpression}.
	 * @param ctx the parse tree
	 */
	void enterDialogExpression(HslParser.DialogExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#dialogExpression}.
	 * @param ctx the parse tree
	 */
	void exitDialogExpression(HslParser.DialogExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#objectExpression}.
	 * @param ctx the parse tree
	 */
	void enterObjectExpression(HslParser.ObjectExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#objectExpression}.
	 * @param ctx the parse tree
	 */
	void exitObjectExpression(HslParser.ObjectExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void enterArrayExpression(HslParser.ArrayExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#arrayExpression}.
	 * @param ctx the parse tree
	 */
	void exitArrayExpression(HslParser.ArrayExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#timerExpression}.
	 * @param ctx the parse tree
	 */
	void enterTimerExpression(HslParser.TimerExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#timerExpression}.
	 * @param ctx the parse tree
	 */
	void exitTimerExpression(HslParser.TimerExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#eventExpression}.
	 * @param ctx the parse tree
	 */
	void enterEventExpression(HslParser.EventExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#eventExpression}.
	 * @param ctx the parse tree
	 */
	void exitEventExpression(HslParser.EventExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#fileExpression}.
	 * @param ctx the parse tree
	 */
	void enterFileExpression(HslParser.FileExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#fileExpression}.
	 * @param ctx the parse tree
	 */
	void exitFileExpression(HslParser.FileExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(HslParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(HslParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(HslParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(HslParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void enterArgumentList(HslParser.ArgumentListContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#argumentList}.
	 * @param ctx the parse tree
	 */
	void exitArgumentList(HslParser.ArgumentListContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#leftExpr}.
	 * @param ctx the parse tree
	 */
	void enterLeftExpr(HslParser.LeftExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#leftExpr}.
	 * @param ctx the parse tree
	 */
	void exitLeftExpr(HslParser.LeftExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#rightExpr}.
	 * @param ctx the parse tree
	 */
	void enterRightExpr(HslParser.RightExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#rightExpr}.
	 * @param ctx the parse tree
	 */
	void exitRightExpr(HslParser.RightExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(HslParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(HslParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(HslParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(HslParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void enterIterationStatement(HslParser.IterationStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#iterationStatement}.
	 * @param ctx the parse tree
	 */
	void exitIterationStatement(HslParser.IterationStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#optInitExpression}.
	 * @param ctx the parse tree
	 */
	void enterOptInitExpression(HslParser.OptInitExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#optInitExpression}.
	 * @param ctx the parse tree
	 */
	void exitOptInitExpression(HslParser.OptInitExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#optExpression}.
	 * @param ctx the parse tree
	 */
	void enterOptExpression(HslParser.OptExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#optExpression}.
	 * @param ctx the parse tree
	 */
	void exitOptExpression(HslParser.OptExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#optForExpression}.
	 * @param ctx the parse tree
	 */
	void enterOptForExpression(HslParser.OptForExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#optForExpression}.
	 * @param ctx the parse tree
	 */
	void exitOptForExpression(HslParser.OptForExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#functionReference}.
	 * @param ctx the parse tree
	 */
	void enterFunctionReference(HslParser.FunctionReferenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#functionReference}.
	 * @param ctx the parse tree
	 */
	void exitFunctionReference(HslParser.FunctionReferenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#bindings}.
	 * @param ctx the parse tree
	 */
	void enterBindings(HslParser.BindingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#bindings}.
	 * @param ctx the parse tree
	 */
	void exitBindings(HslParser.BindingsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#sequenceId}.
	 * @param ctx the parse tree
	 */
	void enterSequenceId(HslParser.SequenceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#sequenceId}.
	 * @param ctx the parse tree
	 */
	void exitSequenceId(HslParser.SequenceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#deviceId}.
	 * @param ctx the parse tree
	 */
	void enterDeviceId(HslParser.DeviceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#deviceId}.
	 * @param ctx the parse tree
	 */
	void exitDeviceId(HslParser.DeviceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#resourceId}.
	 * @param ctx the parse tree
	 */
	void enterResourceId(HslParser.ResourceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#resourceId}.
	 * @param ctx the parse tree
	 */
	void exitResourceId(HslParser.ResourceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#dialogId}.
	 * @param ctx the parse tree
	 */
	void enterDialogId(HslParser.DialogIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#dialogId}.
	 * @param ctx the parse tree
	 */
	void exitDialogId(HslParser.DialogIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#objectId}.
	 * @param ctx the parse tree
	 */
	void enterObjectId(HslParser.ObjectIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#objectId}.
	 * @param ctx the parse tree
	 */
	void exitObjectId(HslParser.ObjectIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#arrayId}.
	 * @param ctx the parse tree
	 */
	void enterArrayId(HslParser.ArrayIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#arrayId}.
	 * @param ctx the parse tree
	 */
	void exitArrayId(HslParser.ArrayIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#timerId}.
	 * @param ctx the parse tree
	 */
	void enterTimerId(HslParser.TimerIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#timerId}.
	 * @param ctx the parse tree
	 */
	void exitTimerId(HslParser.TimerIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#eventId}.
	 * @param ctx the parse tree
	 */
	void enterEventId(HslParser.EventIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#eventId}.
	 * @param ctx the parse tree
	 */
	void exitEventId(HslParser.EventIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#fileId}.
	 * @param ctx the parse tree
	 */
	void enterFileId(HslParser.FileIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#fileId}.
	 * @param ctx the parse tree
	 */
	void exitFileId(HslParser.FileIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#stringId}.
	 * @param ctx the parse tree
	 */
	void enterStringId(HslParser.StringIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#stringId}.
	 * @param ctx the parse tree
	 */
	void exitStringId(HslParser.StringIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#funcId}.
	 * @param ctx the parse tree
	 */
	void enterFuncId(HslParser.FuncIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#funcId}.
	 * @param ctx the parse tree
	 */
	void exitFuncId(HslParser.FuncIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#protoId}.
	 * @param ctx the parse tree
	 */
	void enterProtoId(HslParser.ProtoIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#protoId}.
	 * @param ctx the parse tree
	 */
	void exitProtoId(HslParser.ProtoIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#typeId}.
	 * @param ctx the parse tree
	 */
	void enterTypeId(HslParser.TypeIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#typeId}.
	 * @param ctx the parse tree
	 */
	void exitTypeId(HslParser.TypeIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#namespaceId}.
	 * @param ctx the parse tree
	 */
	void enterNamespaceId(HslParser.NamespaceIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#namespaceId}.
	 * @param ctx the parse tree
	 */
	void exitNamespaceId(HslParser.NamespaceIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(HslParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(HslParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#cString}.
	 * @param ctx the parse tree
	 */
	void enterCString(HslParser.CStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#cString}.
	 * @param ctx the parse tree
	 */
	void exitCString(HslParser.CStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(HslParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(HslParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterDeclSpecifier(HslParser.DeclSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#declSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitDeclSpecifier(HslParser.DeclSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(HslParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(HslParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link HslParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(HslParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link HslParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(HslParser.ParameterContext ctx);
}