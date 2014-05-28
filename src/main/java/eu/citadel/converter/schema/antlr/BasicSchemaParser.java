// Generated by ANTLR 4.1
package eu.citadel.converter.schema.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class BasicSchemaParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__6=1, T__5=2, T__4=3, T__3=4, T__2=5, T__1=6, T__0=7, SPACES=8, BOOLEAN=9, 
		TRUE=10, FALSE=11, NULL=12, NUMBER=13, FLOAT=14, INTEGER=15, TEXT=16;
	public static final String[] tokenNames = {
		"<INVALID>", "'\"id\":'", "']'", "'{'", "','", "'['", "':'", "'}'", "SPACES", 
		"BOOLEAN", "'true'", "'false'", "'null'", "NUMBER", "FLOAT", "INTEGER", 
		"TEXT"
	};
	public static final int
		RULE_rules = 0, RULE_element = 1, RULE_id = 2, RULE_attribute = 3, RULE_value = 4, 
		RULE_list = 5, RULE_object = 6, RULE_option = 7;
	public static final String[] ruleNames = {
		"rules", "element", "id", "attribute", "value", "list", "object", "option"
	};

	@Override
	public String getGrammarFileName() { return "BasicSchema.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public BasicSchemaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RulesContext extends ParserRuleContext {
		public RulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rules; }
	 
		public RulesContext() { }
		public void copyFrom(RulesContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FullRulesContext extends RulesContext {
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public TerminalNode TEXT() { return getToken(BasicSchemaParser.TEXT, 0); }
		public FullRulesContext(RulesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterFullRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitFullRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitFullRules(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyRulesContext extends RulesContext {
		public TerminalNode TEXT() { return getToken(BasicSchemaParser.TEXT, 0); }
		public EmptyRulesContext(RulesContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterEmptyRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitEmptyRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitEmptyRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RulesContext rules() throws RecognitionException {
		RulesContext _localctx = new RulesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_rules);
		int _la;
		try {
			setState(46);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new FullRulesContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(16); match(3);
				setState(31);
				_la = _input.LA(1);
				if (_la==TEXT) {
					{
					setState(17); match(TEXT);
					setState(18); match(6);
					setState(19); match(5);
					setState(28);
					_la = _input.LA(1);
					if (_la==3) {
						{
						setState(20); element();
						setState(25);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==4) {
							{
							{
							setState(21); match(4);
							setState(22); element();
							}
							}
							setState(27);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(30); match(2);
					}
				}

				setState(33); match(7);
				}
				break;

			case 2:
				_localctx = new EmptyRulesContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				_la = _input.LA(1);
				if (_la==3) {
					{
					setState(34); match(3);
					setState(41);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						setState(39);
						_la = _input.LA(1);
						if (_la==TEXT) {
							{
							setState(35); match(TEXT);
							setState(36); match(6);
							setState(37); match(5);
							setState(38); match(2);
							}
						}

						}
						break;
					}
					setState(43); match(7);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_element);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); match(3);
			setState(49); id();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==4) {
				{
				{
				setState(50); match(4);
				setState(51); attribute();
				}
				}
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(57); match(7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59); match(1);
			setState(60); value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AttributeContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(BasicSchemaParser.TEXT, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitAttribute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitAttribute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_attribute);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62); match(TEXT);
			setState(63); match(6);
			setState(64); value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ListValueContext extends ValueContext {
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ListValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterListValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitListValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitListValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ObjectValueContext extends ValueContext {
		public ObjectContext object() {
			return getRuleContext(ObjectContext.class,0);
		}
		public ObjectValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterObjectValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitObjectValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitObjectValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberValueContext extends ValueContext {
		public TerminalNode NUMBER() { return getToken(BasicSchemaParser.NUMBER, 0); }
		public NumberValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterNumberValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitNumberValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitNumberValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TextValueContext extends ValueContext {
		public TerminalNode TEXT() { return getToken(BasicSchemaParser.TEXT, 0); }
		public TextValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterTextValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitTextValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitTextValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BooleanValueContext extends ValueContext {
		public TerminalNode BOOLEAN() { return getToken(BasicSchemaParser.BOOLEAN, 0); }
		public BooleanValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterBooleanValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitBooleanValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitBooleanValue(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullValueContext extends ValueContext {
		public TerminalNode NULL() { return getToken(BasicSchemaParser.NULL, 0); }
		public NullValueContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterNullValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitNullValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitNullValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_value);
		try {
			setState(72);
			switch (_input.LA(1)) {
			case 5:
				_localctx = new ListValueContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66); list();
				}
				break;
			case 3:
				_localctx = new ObjectValueContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(67); object();
				}
				break;
			case NUMBER:
				_localctx = new NumberValueContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(68); match(NUMBER);
				}
				break;
			case BOOLEAN:
				_localctx = new BooleanValueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(69); match(BOOLEAN);
				}
				break;
			case NULL:
				_localctx = new NullValueContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(70); match(NULL);
				}
				break;
			case TEXT:
				_localctx = new TextValueContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(71); match(TEXT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListContext extends ParserRuleContext {
		public ListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_list; }
	 
		public ListContext() { }
		public void copyFrom(ListContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EmptyListContext extends ListContext {
		public EmptyListContext(ListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterEmptyList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitEmptyList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitEmptyList(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FullListContext extends ListContext {
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public FullListContext(ListContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterFullList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitFullList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitFullList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_list);
		int _la;
		try {
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				_localctx = new FullListContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(74); match(5);
				setState(75); value();
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==4) {
					{
					{
					setState(76); match(4);
					setState(77); value();
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(83); match(2);
				}
				break;

			case 2:
				_localctx = new EmptyListContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(85); match(5);
				setState(86); match(2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ObjectContext extends ParserRuleContext {
		public ObjectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object; }
	 
		public ObjectContext() { }
		public void copyFrom(ObjectContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FullObjectContext extends ObjectContext {
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public FullObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterFullObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitFullObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitFullObject(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyObjectContext extends ObjectContext {
		public EmptyObjectContext(ObjectContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterEmptyObject(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitEmptyObject(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitEmptyObject(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObjectContext object() throws RecognitionException {
		ObjectContext _localctx = new ObjectContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_object);
		int _la;
		try {
			setState(103);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				_localctx = new FullObjectContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89); match(3);
				setState(98);
				_la = _input.LA(1);
				if (_la==TEXT) {
					{
					setState(90); option();
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==4) {
						{
						{
						setState(91); match(4);
						setState(92); option();
						}
						}
						setState(97);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(100); match(7);
				}
				break;

			case 2:
				_localctx = new EmptyObjectContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(101); match(3);
				setState(102); match(7);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode TEXT() { return getToken(BasicSchemaParser.TEXT, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof BasicSchemaListener ) ((BasicSchemaListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof BasicSchemaVisitor ) return ((BasicSchemaVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_option);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); match(TEXT);
			setState(106); match(6);
			setState(107); value();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\22p\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\7\2\32\n\2\f\2\16\2\35\13\2\5\2\37\n\2\3\2\5\2\"\n\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\5\2*\n\2\5\2,\n\2\3\2\5\2/\n\2\5\2\61\n\2\3\3\3\3\3\3"+
		"\3\3\7\3\67\n\3\f\3\16\3:\13\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\5\6K\n\6\3\7\3\7\3\7\3\7\7\7Q\n\7\f\7\16\7T\13\7"+
		"\3\7\3\7\3\7\3\7\5\7Z\n\7\3\b\3\b\3\b\3\b\7\b`\n\b\f\b\16\bc\13\b\5\b"+
		"e\n\b\3\b\3\b\3\b\5\bj\n\b\3\t\3\t\3\t\3\t\3\t\2\n\2\4\6\b\n\f\16\20\2"+
		"\2y\2\60\3\2\2\2\4\62\3\2\2\2\6=\3\2\2\2\b@\3\2\2\2\nJ\3\2\2\2\fY\3\2"+
		"\2\2\16i\3\2\2\2\20k\3\2\2\2\22!\7\5\2\2\23\24\7\22\2\2\24\25\7\b\2\2"+
		"\25\36\7\7\2\2\26\33\5\4\3\2\27\30\7\6\2\2\30\32\5\4\3\2\31\27\3\2\2\2"+
		"\32\35\3\2\2\2\33\31\3\2\2\2\33\34\3\2\2\2\34\37\3\2\2\2\35\33\3\2\2\2"+
		"\36\26\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 \"\7\4\2\2!\23\3\2\2\2!\"\3\2"+
		"\2\2\"#\3\2\2\2#\61\7\t\2\2$+\7\5\2\2%&\7\22\2\2&\'\7\b\2\2\'(\7\7\2\2"+
		"(*\7\4\2\2)%\3\2\2\2)*\3\2\2\2*,\3\2\2\2+)\3\2\2\2+,\3\2\2\2,-\3\2\2\2"+
		"-/\7\t\2\2.$\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60\22\3\2\2\2\60.\3\2\2\2\61"+
		"\3\3\2\2\2\62\63\7\5\2\2\638\5\6\4\2\64\65\7\6\2\2\65\67\5\b\5\2\66\64"+
		"\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29;\3\2\2\2:8\3\2\2\2;<\7\t\2"+
		"\2<\5\3\2\2\2=>\7\3\2\2>?\5\n\6\2?\7\3\2\2\2@A\7\22\2\2AB\7\b\2\2BC\5"+
		"\n\6\2C\t\3\2\2\2DK\5\f\7\2EK\5\16\b\2FK\7\17\2\2GK\7\13\2\2HK\7\16\2"+
		"\2IK\7\22\2\2JD\3\2\2\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2"+
		"\2\2K\13\3\2\2\2LM\7\7\2\2MR\5\n\6\2NO\7\6\2\2OQ\5\n\6\2PN\3\2\2\2QT\3"+
		"\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2\2TR\3\2\2\2UV\7\4\2\2VZ\3\2\2\2WX\7"+
		"\7\2\2XZ\7\4\2\2YL\3\2\2\2YW\3\2\2\2Z\r\3\2\2\2[d\7\5\2\2\\a\5\20\t\2"+
		"]^\7\6\2\2^`\5\20\t\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2"+
		"\2ca\3\2\2\2d\\\3\2\2\2de\3\2\2\2ef\3\2\2\2fj\7\t\2\2gh\7\5\2\2hj\7\t"+
		"\2\2i[\3\2\2\2ig\3\2\2\2j\17\3\2\2\2kl\7\22\2\2lm\7\b\2\2mn\5\n\6\2n\21"+
		"\3\2\2\2\20\33\36!)+.\608JRYadi";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}