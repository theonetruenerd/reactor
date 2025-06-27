// Generated from C:/Users/TarunChapman/IdeaProjects/reactor/src/main/java/com/tc/reactor/support/languages/xaml/syntaxchecker/Xaml.g4 by ANTLR 4.13.2
package com.tc.reactor.support.languages.xaml.syntaxchecker;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XamlParser}.
 */
public interface XamlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link XamlParser#xamlFile}.
	 * @param ctx the parse tree
	 */
	void enterXamlFile(XamlParser.XamlFileContext ctx);
	/**
	 * Exit a parse tree produced by {@link XamlParser#xamlFile}.
	 * @param ctx the parse tree
	 */
	void exitXamlFile(XamlParser.XamlFileContext ctx);
	/**
	 * Enter a parse tree produced by {@link XamlParser#element}.
	 * @param ctx the parse tree
	 */
	void enterElement(XamlParser.ElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link XamlParser#element}.
	 * @param ctx the parse tree
	 */
	void exitElement(XamlParser.ElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link XamlParser#tagName}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XamlParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link XamlParser#tagName}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XamlParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link XamlParser#attributeList}.
	 * @param ctx the parse tree
	 */
	void enterAttributeList(XamlParser.AttributeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link XamlParser#attributeList}.
	 * @param ctx the parse tree
	 */
	void exitAttributeList(XamlParser.AttributeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link XamlParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(XamlParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link XamlParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(XamlParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link XamlParser#namespace}.
	 * @param ctx the parse tree
	 */
	void enterNamespace(XamlParser.NamespaceContext ctx);
	/**
	 * Exit a parse tree produced by {@link XamlParser#namespace}.
	 * @param ctx the parse tree
	 */
	void exitNamespace(XamlParser.NamespaceContext ctx);
}