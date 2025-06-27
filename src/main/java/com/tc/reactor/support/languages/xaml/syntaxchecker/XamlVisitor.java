// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/xaml/syntaxchecker/Xaml.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.xaml.syntaxchecker;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XamlParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XamlVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link XamlParser#xamlFile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXamlFile(XamlParser.XamlFileContext ctx);
	/**
	 * Visit a parse tree produced by {@link XamlParser#element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElement(XamlParser.ElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link XamlParser#tagName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(XamlParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link XamlParser#attributeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeList(XamlParser.AttributeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link XamlParser#attribute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttribute(XamlParser.AttributeContext ctx);
	/**
	 * Visit a parse tree produced by {@link XamlParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(XamlParser.NamespaceContext ctx);
}