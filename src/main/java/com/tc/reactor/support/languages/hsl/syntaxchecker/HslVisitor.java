// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/hsl/syntaxchecker/Hsl.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.hsl.syntaxchecker;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HslParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HslVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HslParser#hslFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHslFile(HslParser.HslFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(HslParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#controlLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlLine(HslParser.ControlLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HslParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(HslParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(HslParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(HslParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#flowControlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowControlStatement(HslParser.FlowControlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#controlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlStatement(HslParser.ControlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(HslParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#formalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalList(HslParser.FormalListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(HslParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#declSpecifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclSpecifiers(HslParser.DeclSpecifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceDefinition(HslParser.NamespaceDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructure(HslParser.StructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(HslParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#tagDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagDeclarationList(HslParser.TagDeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#tagDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagDeclaration(HslParser.TagDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(HslParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#storage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorage(HslParser.StorageContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(HslParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#errorHandler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErrorHandler(HslParser.ErrorHandlerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(HslParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#sequenceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceExpression(HslParser.SequenceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#stringExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpression(HslParser.StringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#deviceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeviceExpression(HslParser.DeviceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#resourceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResourceExpression(HslParser.ResourceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#dialogExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDialogExpression(HslParser.DialogExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#objectExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectExpression(HslParser.ObjectExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#arrayExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpression(HslParser.ArrayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#timerExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimerExpression(HslParser.TimerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#eventExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventExpression(HslParser.EventExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#fileExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileExpression(HslParser.FileExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(HslParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(HslParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#argumentList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgumentList(HslParser.ArgumentListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#leftExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftExpr(HslParser.LeftExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#rightExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightExpr(HslParser.RightExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(HslParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(HslParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(HslParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#optInitExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptInitExpression(HslParser.OptInitExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#optExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptExpression(HslParser.OptExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#optForExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptForExpression(HslParser.OptForExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#functionReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionReference(HslParser.FunctionReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#bindings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBindings(HslParser.BindingsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#sequenceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceId(HslParser.SequenceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#deviceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeviceId(HslParser.DeviceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#resourceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResourceId(HslParser.ResourceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#dialogId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDialogId(HslParser.DialogIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#objectId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectId(HslParser.ObjectIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#arrayId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayId(HslParser.ArrayIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#timerId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimerId(HslParser.TimerIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#eventId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventId(HslParser.EventIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#fileId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileId(HslParser.FileIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#stringId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringId(HslParser.StringIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#funcId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncId(HslParser.FuncIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#protoId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtoId(HslParser.ProtoIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#typeId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeId(HslParser.TypeIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#namespaceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceId(HslParser.NamespaceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(HslParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#cString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCString(HslParser.CStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(HslParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#declSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclSpecifier(HslParser.DeclSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HslParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(HslParser.ParameterContext ctx);
}