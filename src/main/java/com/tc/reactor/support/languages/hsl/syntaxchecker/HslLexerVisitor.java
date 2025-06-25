// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/hsl/syntaxchecker/HslLexer.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.hsl.syntaxchecker;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HslLexerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HslLexerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#hslFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHslFile(HslLexerParser.HslFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(HslLexerParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#controlLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlLine(HslLexerParser.ControlLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HslLexerParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(HslLexerParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(HslLexerParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(HslLexerParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#flowControlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFlowControlStatement(HslLexerParser.FlowControlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#controlStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlStatement(HslLexerParser.ControlStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(HslLexerParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#declSpecifiers}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclSpecifiers(HslLexerParser.DeclSpecifiersContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(HslLexerParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#namespaceDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceDefinition(HslLexerParser.NamespaceDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#structure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructure(HslLexerParser.StructureContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(HslLexerParser.ArrayContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#tagDeclarationList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagDeclarationList(HslLexerParser.TagDeclarationListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#tagDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagDeclaration(HslLexerParser.TagDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(HslLexerParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#storage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStorage(HslLexerParser.StorageContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#formalList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalList(HslLexerParser.FormalListContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(HslLexerParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#errorHandler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErrorHandler(HslLexerParser.ErrorHandlerContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(HslLexerParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#sequenceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceExpression(HslLexerParser.SequenceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#stringExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpression(HslLexerParser.StringExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#deviceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeviceExpression(HslLexerParser.DeviceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#resourceExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResourceExpression(HslLexerParser.ResourceExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#dialogExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDialogExpression(HslLexerParser.DialogExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#objectExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectExpression(HslLexerParser.ObjectExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#arrayExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayExpression(HslLexerParser.ArrayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#timerExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimerExpression(HslLexerParser.TimerExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#eventExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventExpression(HslLexerParser.EventExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#fileExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileExpression(HslLexerParser.FileExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(HslLexerParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#leftExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftExpr(HslLexerParser.LeftExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#rightExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightExpr(HslLexerParser.RightExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(HslLexerParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(HslLexerParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#iterationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIterationStatement(HslLexerParser.IterationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#optInitExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptInitExpression(HslLexerParser.OptInitExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#optExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptExpression(HslLexerParser.OptExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#optForExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptForExpression(HslLexerParser.OptForExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#functionReference}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionReference(HslLexerParser.FunctionReferenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#bindings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBindings(HslLexerParser.BindingsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#sequenceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSequenceId(HslLexerParser.SequenceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#deviceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeviceId(HslLexerParser.DeviceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#resourceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResourceId(HslLexerParser.ResourceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#dialogId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDialogId(HslLexerParser.DialogIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#objectId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectId(HslLexerParser.ObjectIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#arrayId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayId(HslLexerParser.ArrayIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#timerId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTimerId(HslLexerParser.TimerIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#eventId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEventId(HslLexerParser.EventIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#fileId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFileId(HslLexerParser.FileIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#stringId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringId(HslLexerParser.StringIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#funcId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncId(HslLexerParser.FuncIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#protoId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtoId(HslLexerParser.ProtoIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#typeId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeId(HslLexerParser.TypeIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#namespaceId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespaceId(HslLexerParser.NamespaceIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(HslLexerParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#cString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCString(HslLexerParser.CStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(HslLexerParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#declSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclSpecifier(HslLexerParser.DeclSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link HslLexerParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(HslLexerParser.TypeContext ctx);
}