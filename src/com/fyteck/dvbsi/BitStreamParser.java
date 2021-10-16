/*******************************************************************************
 * Copyright (c) 2021 FuYou Technology
 *
 *
 *   The content of this file includes portions of the FY Technology
 *   released in source code form as part of the SDK installer package.
 *
 *   Commercial License Usage
 *
 *   Licensees holding valid commercial licenses to the FY Technology
 *   may use this file in accordance with the end user license agreement provided 
 *   with the software or, alternatively, in accordance with the terms contained in a
 *   written agreement between you and FY Inc.
 *
 *
 *
 *   GNU General Public License Usage
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation; either version 2 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program; if not, write to the Free Software
 *   Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
 *******************************************************************************/
package com.fyteck.dvbsi;

//import org.apache.log4j.Logger;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({ "all", "warnings", "unchecked" })
public class BitStreamParser extends Parser {
    public static final String[] tokenNames                                   = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BAG_END",
            "BAG_START", "BLK", "DESCRIPTOR", "EQ", "EQEQ", "FOR", "HexDigit", "HexLiteral", "ID", "INT", "KEY_BREAK", "KEY_CALL", "KEY_DEF",
            "KEY_ELSE", "KEY_ELSEIF", "KEY_FCT", "KEY_FOR", "KEY_FOR_BUFFER", "KEY_FOR_DESCRIPTOR", "KEY_FOR_END", "KEY_FOR_START", "KEY_IDENT",
            "KEY_IF", "KEY_LB", "KEY_LOGIC", "KEY_NODE_END", "KEY_NODE_START", "KEY_POP", "KEY_PUSH", "KEY_RB", "KEY_REWIND", "KEY_WHILE", "LBRACE",
            "LT", "NEWLINE", "NUMBER", "PLUS", "PROG", "RBRACE", "SYNTAX_NAME", "WS", "'!='", "'&&'", "'('", "'()'", "')'", "'*'", "'/'", "';'",
            "'<<<'", "'<='", "'>'", "'>='", "'>>>'", "'CALL '", "'If'", "'NODE_END'", "'NODE_START'", "'break'", "'else'", "'if'", "'rewind'",
            "'while'", "'||'" };

    public static final int      EOF                                          = -1;
    public static final int      T__46                                        = 46;
    public static final int      T__47                                        = 47;
    public static final int      T__48                                        = 48;
    public static final int      T__49                                        = 49;
    public static final int      T__50                                        = 50;
    public static final int      T__51                                        = 51;
    public static final int      T__52                                        = 52;
    public static final int      T__53                                        = 53;
    public static final int      T__54                                        = 54;
    public static final int      T__55                                        = 55;
    public static final int      T__56                                        = 56;
    public static final int      T__57                                        = 57;
    public static final int      T__58                                        = 58;
    public static final int      T__59                                        = 59;
    public static final int      T__60                                        = 60;
    public static final int      T__61                                        = 61;
    public static final int      T__62                                        = 62;
    public static final int      T__63                                        = 63;
    public static final int      T__64                                        = 64;
    public static final int      T__65                                        = 65;
    public static final int      T__66                                        = 66;
    public static final int      T__67                                        = 67;
    public static final int      T__68                                        = 68;
    public static final int      BAG_END                                      = 4;
    public static final int      BAG_START                                    = 5;
    public static final int      BLK                                          = 6;
    public static final int      DESCRIPTOR                                   = 7;
    public static final int      EQ                                           = 8;
    public static final int      EQEQ                                         = 9;
    public static final int      FOR                                          = 10;
    public static final int      HexDigit                                     = 11;
    public static final int      HexLiteral                                   = 12;
    public static final int      ID                                           = 13;
    public static final int      INT                                          = 14;
    public static final int      KEY_BREAK                                    = 15;
    public static final int      KEY_CALL                                     = 16;
    public static final int      KEY_DEF                                      = 17;
    public static final int      KEY_ELSE                                     = 18;
    public static final int      KEY_ELSEIF                                   = 19;
    public static final int      KEY_FCT                                      = 20;
    public static final int      KEY_FOR                                      = 21;
    public static final int      KEY_FOR_BUFFER                               = 22;
    public static final int      KEY_FOR_DESCRIPTOR                           = 23;
    public static final int      KEY_FOR_END                                  = 24;
    public static final int      KEY_FOR_START                                = 25;
    public static final int      KEY_IDENT                                    = 26;
    public static final int      KEY_IF                                       = 27;
    public static final int      KEY_LB                                       = 28;
    public static final int      KEY_LOGIC                                    = 29;
    public static final int      KEY_NODE_END                                 = 30;
    public static final int      KEY_NODE_START                               = 31;
    public static final int      KEY_POP                                      = 32;
    public static final int      KEY_PUSH                                     = 33;
    public static final int      KEY_RB                                       = 34;
    public static final int      KEY_REWIND                                   = 35;
    public static final int      KEY_WHILE                                    = 36;
    public static final int      LBRACE                                       = 37;
    public static final int      LT                                           = 38;
    public static final int      NEWLINE                                      = 39;
    public static final int      NUMBER                                       = 40;
    public static final int      PLUS                                         = 41;
    public static final int      PROG                                         = 42;
    public static final int      RBRACE                                       = 43;
    public static final int      SYNTAX_NAME                                  = 44;
    public static final int      WS                                           = 45;
    public static final BitSet   FOLLOW_ID_in_identiy214                      = new BitSet(new long[] { 0x0000000000000100L });

    // delegators
    public static final BitSet   FOLLOW_EQ_in_identiy216                      = new BitSet(new long[] { 0x0000000000001000L });
    public static final BitSet   FOLLOW_HexLiteral_in_identiy220              = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_identiy_in_program259                 = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_program263                      = new BitSet(new long[] { 0x0002000000000000L });
    public static final BitSet   FOLLOW_49_in_program265                      = new BitSet(new long[] { 0x0000002000000000L });
    public static final BitSet   FOLLOW_block_in_program267                   = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_statement_in_body306                  = new BitSet(new long[] { 0xFC40000000002402L, 0x000000000000000EL });
    public static final BitSet   FOLLOW_ID_in_define325                       = new BitSet(new long[] { 0x0000000000004000L });
    public static final BitSet   FOLLOW_INT_in_define329                      = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_define333                       = new BitSet(new long[] { 0x0010000000000002L });

    ;

    // $ANTLR start "identiy"
    public static final BitSet   FOLLOW_bagName_in_define335                  = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "identiy"
    public static final BitSet   FOLLOW_forDescriptStat_in_statement375       = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "program"
    public static final BitSet   FOLLOW_forStat_in_statement381               = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "program"
    public static final BitSet   FOLLOW_funcStat_in_statement387              = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "body"
    public static final BitSet   FOLLOW_condStat_in_statement393              = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "body"
    public static final BitSet   FOLLOW_define_in_statement399                = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "define"
    public static final BitSet   FOLLOW_bagStart_in_statement405              = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "define"
    public static final BitSet   FOLLOW_bagEnd_in_statement411                = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "statement"
    public static final BitSet   FOLLOW_whileStat_in_statement417             = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "statement"
    public static final BitSet   FOLLOW_breakStmt_in_statement423             = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "callSyntax"
    public static final BitSet   FOLLOW_rewindStmt_in_statement429            = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "callSyntax"
    public static final BitSet   FOLLOW_nodeStart_in_statement435             = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "whileStat"
    public static final BitSet   FOLLOW_nodeEnd_in_statement441               = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "whileStat"
    public static final BitSet   FOLLOW_callSyntax_in_statement447            = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "condStat"
    public static final BitSet   FOLLOW_59_in_callSyntax465                   = new BitSet(new long[] { 0x0000100000000000L });
    // $ANTLR end "condStat"
    public static final BitSet   FOLLOW_SYNTAX_NAME_in_callSyntax469          = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "ifStat"
    public static final BitSet   FOLLOW_67_in_whileStat502                    = new BitSet(new long[] { 0x0001000000000000L });
    // $ANTLR end "ifStat"
    public static final BitSet   FOLLOW_48_in_whileStat504                    = new BitSet(new long[] { 0x0000000000002000L });

    ;

    // $ANTLR start "elseStat"
    public static final BitSet   FOLLOW_logical_expression_in_whileStat506    = new BitSet(new long[] { 0x0004000000000000L });
    // $ANTLR end "elseStat"
    public static final BitSet   FOLLOW_50_in_whileStat508                    = new BitSet(new long[] { 0x0000002000000000L });

    ;

    // $ANTLR start "elseifStat"
    public static final BitSet   FOLLOW_block_in_whileStat510                 = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "elseifStat"
    public static final BitSet   FOLLOW_ifStat_in_condStat543                 = new BitSet(new long[] { 0x0000000000000002L, 0x0000000000000001L });

    ;

    // $ANTLR start "compare_operator"
    public static final BitSet   FOLLOW_elseifStat_in_condStat545             = new BitSet(new long[] { 0x0000000000000002L, 0x0000000000000001L });
    // $ANTLR end "compare_operator"
    public static final BitSet   FOLLOW_elseStat_in_condStat548               = new BitSet(new long[] { 0x0000000000000002L, 0x0000000000000001L });

    ;

    // $ANTLR start "logical_expression"
    public static final BitSet   FOLLOW_65_in_ifStat570                       = new BitSet(new long[] { 0x0001000000000000L });
    // $ANTLR end "logical_expression"
    public static final BitSet   FOLLOW_60_in_ifStat578                       = new BitSet(new long[] { 0x0001000000000000L });

    ;

    // $ANTLR start "logicExpr"
    public static final BitSet   FOLLOW_48_in_ifStat586                       = new BitSet(new long[] { 0x0000000000002000L });
    // $ANTLR end "logicExpr"
    public static final BitSet   FOLLOW_logical_expression_in_ifStat588       = new BitSet(new long[] { 0x0004000000000000L });

    ;

    // $ANTLR start "logicValue"
    public static final BitSet   FOLLOW_50_in_ifStat590                       = new BitSet(new long[] { 0x0000002000000000L });
    // $ANTLR end "logicValue"
    public static final BitSet   FOLLOW_block_in_ifStat592                    = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "logicCompare"
    public static final BitSet   FOLLOW_64_in_elseStat631                     = new BitSet(new long[] { 0x0000002000000000L });
    // $ANTLR end "logicCompare"
    public static final BitSet   FOLLOW_block_in_elseStat633                  = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "funcStat"
    public static final BitSet   FOLLOW_64_in_elseifStat666                   = new BitSet(new long[] { 0x0000000000000000L, 0x0000000000000002L });
    // $ANTLR end "funcStat"
    public static final BitSet   FOLLOW_65_in_elseifStat668                   = new BitSet(new long[] { 0x0001000000000000L });

    ;

    // $ANTLR start "breakStmt"
    public static final BitSet   FOLLOW_48_in_elseifStat670                   = new BitSet(new long[] { 0x0000000000002000L });
    // $ANTLR end "breakStmt"
    public static final BitSet   FOLLOW_logical_expression_in_elseifStat672   = new BitSet(new long[] { 0x0004000000000000L });

    ;

    // $ANTLR start "rewindStmt"
    public static final BitSet   FOLLOW_50_in_elseifStat674                   = new BitSet(new long[] { 0x0000002000000000L });
    // $ANTLR end "rewindStmt"
    public static final BitSet   FOLLOW_block_in_elseifStat676                = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "bagStart"
    public static final BitSet   FOLLOW_logicExpr_in_logical_expression760    = new BitSet(new long[] { 0x0000800000000002L, 0x0000000000000010L });
    // $ANTLR end "bagStart"
    public static final BitSet   FOLLOW_logicCompare_in_logical_expression763 = new BitSet(new long[] { 0x0000000000002000L });

    ;

    // $ANTLR start "bagEnd"
    public static final BitSet   FOLLOW_logicExpr_in_logical_expression765    = new BitSet(new long[] { 0x0000800000000002L, 0x0000000000000010L });
    // $ANTLR end "bagEnd"
    public static final BitSet   FOLLOW_ID_in_logicExpr782                    = new BitSet(new long[] { 0x0380404000000200L });

    ;

    // $ANTLR start "bagName"
    public static final BitSet   FOLLOW_compare_operator_in_logicExpr784      = new BitSet(new long[] { 0x0000000000005000L });
    // $ANTLR end "bagName"
    public static final BitSet   FOLLOW_logicValue_in_logicExpr788            = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "forCond"
    public static final BitSet   FOLLOW_ID_in_funcStat868                     = new BitSet(new long[] { 0x0002000000000000L });
    // $ANTLR end "forCond"
    public static final BitSet   FOLLOW_49_in_funcStat870                     = new BitSet(new long[] { 0x0000002000000002L });

    ;

    // $ANTLR start "forStat"
    public static final BitSet   FOLLOW_block_in_funcStat872                  = new BitSet(new long[] { 0x0000000000000002L });
    // $ANTLR end "forStat"
    public static final BitSet   FOLLOW_63_in_breakStmt908                    = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "nodeStart"
    public static final BitSet   FOLLOW_66_in_rewindStmt933                   = new BitSet(new long[] { 0x0001000000000000L });
    // $ANTLR end "nodeStart"
    public static final BitSet   FOLLOW_48_in_rewindStmt935                   = new BitSet(new long[] { 0x0000000000005000L });

    ;

    // $ANTLR start "nodeEnd"
    public static final BitSet   FOLLOW_logicValue_in_rewindStmt939           = new BitSet(new long[] { 0x0004000000000000L });
    // $ANTLR end "nodeEnd"
    public static final BitSet   FOLLOW_50_in_rewindStmt941                   = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "forDescriptStat"
    public static final BitSet   FOLLOW_58_in_bagStart980                     = new BitSet(new long[] { 0x0000000000002000L });
    // $ANTLR end "forDescriptStat"
    public static final BitSet   FOLLOW_ID_in_bagStart982                     = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "forBufferStat"
    public static final BitSet   FOLLOW_54_in_bagEnd1015                      = new BitSet(new long[] { 0x0000000000002000L });
    // $ANTLR end "forBufferStat"
    public static final BitSet   FOLLOW_ID_in_bagEnd1017                      = new BitSet(new long[] { 0x0000000000000002L });

    ;

    // $ANTLR start "block"
    public static final BitSet   FOLLOW_52_in_bagName1050                     = new BitSet(new long[] { 0x0008000000000000L });
    // $ANTLR end "block"

    // Delegated rules
    public static final BitSet   FOLLOW_51_in_bagName1052                     = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_bagName1056                     = new BitSet(new long[] { 0x0008000000000000L });
    public static final BitSet   FOLLOW_51_in_bagName1058                     = new BitSet(new long[] { 0x0010000000000000L });
    public static final BitSet   FOLLOW_52_in_bagName1060                     = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_48_in_forCond1092                     = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_forCond1094                     = new BitSet(new long[] { 0x0000000000000100L });
    public static final BitSet   FOLLOW_EQ_in_forCond1096                     = new BitSet(new long[] { 0x0000000000004000L });
    public static final BitSet   FOLLOW_INT_in_forCond1098                    = new BitSet(new long[] { 0x0020000000000000L });
    public static final BitSet   FOLLOW_53_in_forCond1100                     = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_forCond1102                     = new BitSet(new long[] { 0x0000004000000000L });
    public static final BitSet   FOLLOW_LT_in_forCond1104                     = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_forCond1108                     = new BitSet(new long[] { 0x0020000000000000L });
    public static final BitSet   FOLLOW_53_in_forCond1110                     = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_forCond1112                     = new BitSet(new long[] { 0x0000020000000000L });
    public static final BitSet   FOLLOW_PLUS_in_forCond1114                   = new BitSet(new long[] { 0x0000020000000000L });
    public static final BitSet   FOLLOW_PLUS_in_forCond1116                   = new BitSet(new long[] { 0x0004000000000000L });
    public static final BitSet   FOLLOW_50_in_forCond1118                     = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_FOR_in_forStat1150                    = new BitSet(new long[] { 0x0001000000000000L });
    public static final BitSet   FOLLOW_forCond_in_forStat1152                = new BitSet(new long[] { 0x0010002000000000L });
    public static final BitSet   FOLLOW_bagName_in_forStat1154                = new BitSet(new long[] { 0x0000002000000000L });
    public static final BitSet   FOLLOW_block_in_forStat1157                  = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_62_in_nodeStart1208                   = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_nodeStart1210                   = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_61_in_nodeEnd1248                     = new BitSet(new long[] { 0x0000000000002000L });
    public static final BitSet   FOLLOW_ID_in_nodeEnd1251                     = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_FOR_in_forDescriptStat1286            = new BitSet(new long[] { 0x0001000000000000L });
    public static final BitSet   FOLLOW_forCond_in_forDescriptStat1288        = new BitSet(new long[] { 0x0000002000000000L });
    public static final BitSet   FOLLOW_LBRACE_in_forDescriptStat1290         = new BitSet(new long[] { 0x0000000000000080L });
    public static final BitSet   FOLLOW_DESCRIPTOR_in_forDescriptStat1292     = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet   FOLLOW_RBRACE_in_forDescriptStat1294         = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_FOR_in_forBufferStat1329              = new BitSet(new long[] { 0x0001000000000000L });
    public static final BitSet   FOLLOW_forCond_in_forBufferStat1331          = new BitSet(new long[] { 0x0000002000000000L });
    public static final BitSet   FOLLOW_LBRACE_in_forBufferStat1333           = new BitSet(new long[] { 0xFFFFFFFFFFFFFFF0L, 0x000000000000001FL });
    public static final BitSet   FOLLOW_RBRACE_in_forBufferStat1338           = new BitSet(new long[] { 0x0000000000000002L });
    public static final BitSet   FOLLOW_LBRACE_in_block1373                   = new BitSet(new long[] { 0xFC40000000002400L, 0x000000000000000EL });
    public static final BitSet   FOLLOW_body_in_block1375                     = new BitSet(new long[] { 0x0000080000000000L });
    public static final BitSet   FOLLOW_RBRACE_in_block1377                   = new BitSet(new long[] { 0x0000000000000002L });
    protected TreeAdaptor        adaptor                                      = new CommonTreeAdaptor();
    private List<String>         errors                                       = new LinkedList<String>();

    public BitStreamParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }

    public BitStreamParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
    }

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }

    public String[] getTokenNames() {
        return BitStreamParser.tokenNames;
    }

    public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
        String hdr = getErrorHeader(e);
        String msg = getErrorMessage(e, tokenNames);
        errors.add(hdr + " " + msg);
    }

    public List<String> getErrors() {
        return errors;
    }

    // KEY_IDENT ID HexLiteral NEWLINE ) ;
    public final BitStreamParser.identiy_return identiy() throws RecognitionException {
        BitStreamParser.identiy_return retval = new BitStreamParser.identiy_return();
        retval.start = input.LT(1);

        CommonTree             root_0             = null;

        Token                  var_key            = null;
        Token                  var_value          = null;
        Token                  char_literal1      = null;

        CommonTree             var_key_tree       = null;
        CommonTree             var_value_tree     = null;
        CommonTree             char_literal1_tree = null;
        RewriteRuleTokenStream stream_HexLiteral  = new RewriteRuleTokenStream(adaptor, "token HexLiteral");
        RewriteRuleTokenStream stream_EQ          = new RewriteRuleTokenStream(adaptor, "token EQ");
        RewriteRuleTokenStream stream_ID          = new RewriteRuleTokenStream(adaptor, "token ID");

        try {

            // KEY_IDENT ID HexLiteral NEWLINE ) )

            {

                {
                    var_key = (Token) match(input, ID, FOLLOW_ID_in_identiy214);
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_ID.add(var_key);

                    char_literal1 = (Token) match(input, EQ, FOLLOW_EQ_in_identiy216);
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_EQ.add(char_literal1);

                    var_value = (Token) match(input, HexLiteral, FOLLOW_HexLiteral_in_identiy220);
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_HexLiteral.add(var_value);

                }

                // AST REWRITE
                // elements: ID, HexLiteral
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 64:5: -> ^( KEY_IDENT ID HexLiteral NEWLINE )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_IDENT, "KEY_IDENT"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_1, stream_HexLiteral.nextNode());

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(NEWLINE, "NEWLINE"));

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // )? ID block ) ;
    public final BitStreamParser.program_return program() throws RecognitionException {
        BitStreamParser.program_return retval = new BitStreamParser.program_return();
        retval.start = input.LT(1);

        CommonTree                     root_0               = null;

        Token                          ID3                  = null;
        Token                          string_literal4      = null;
        BitStreamParser.identiy_return identiy2             = null;

        BitStreamParser.block_return   block5               = null;

        CommonTree                     ID3_tree             = null;
        CommonTree                     string_literal4_tree = null;
        RewriteRuleTokenStream         stream_49            = new RewriteRuleTokenStream(adaptor, "token 49");
        RewriteRuleTokenStream         stream_ID            = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleSubtreeStream       stream_identiy       = new RewriteRuleSubtreeStream(adaptor, "rule identiy");
        RewriteRuleSubtreeStream       stream_block         = new RewriteRuleSubtreeStream(adaptor, "rule block");
        try {

            // )? ID block ) )

            {

                int alt1  = 2;
                int LA1_0 = input.LA(1);

                if ((LA1_0 == ID)) {
                    int LA1_1 = input.LA(2);

                    if ((LA1_1 == EQ)) {
                        alt1 = 1;
                    }
                }
                switch (alt1) {
                case 1: {
                    pushFollow(FOLLOW_identiy_in_program259);
                    identiy2 = identiy();

                    state._fsp--;
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_identiy.add(identiy2.getTree());

                }
                    break;

                }

                ID3 = (Token) match(input, ID, FOLLOW_ID_in_program263);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID3);

                string_literal4 = (Token) match(input, 49, FOLLOW_49_in_program265);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_49.add(string_literal4);

                pushFollow(FOLLOW_block_in_program267);
                block5 = block();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_block.add(block5.getTree());

                // AST REWRITE
                // elements: identiy, block, ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 71:5: -> ^( PROG ( identiy )? ID block )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(PROG, "PROG"), root_1);

                            if (stream_identiy.hasNext()) {
                                adaptor.addChild(root_1, stream_identiy.nextTree());

                            }
                            stream_identiy.reset();

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_1, stream_block.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.body_return body() throws RecognitionException {
        BitStreamParser.body_return retval = new BitStreamParser.body_return();
        retval.start = input.LT(1);

        CommonTree                       root_0     = null;

        BitStreamParser.statement_return statement6 = null;

        try {

            {
                root_0 = (CommonTree) adaptor.nil();

                int cnt2 = 0;
                loop2: do {
                    int alt2  = 2;
                    int LA2_0 = input.LA(1);

                    if ((LA2_0 == FOR || LA2_0 == ID || LA2_0 == 54 || (LA2_0 >= 58 && LA2_0 <= 63) || (LA2_0 >= 65 && LA2_0 <= 67))) {
                        alt2 = 1;
                    }

                    switch (alt2) {
                    case 1: {
                        pushFollow(FOLLOW_statement_in_body306);
                        statement6 = statement();

                        state._fsp--;
                        if (state.failed)
                            return retval;
                        if (state.backtracking == 0)
                            adaptor.addChild(root_0, statement6.getTree());

                    }
                        break;

                    default:
                        if (cnt2 >= 1)
                            break loop2;
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        EarlyExitException eee = new EarlyExitException(2, input);
                        throw eee;
                    }
                    cnt2++;
                } while (true);

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // )? -> ^( KEY_DEF ID INT ID ) ;
    public final BitStreamParser.define_return define() throws RecognitionException {
        BitStreamParser.define_return retval = new BitStreamParser.define_return();
        retval.start = input.LT(1);

        CommonTree                     root_0         = null;

        Token                          var_key        = null;
        Token                          var_len        = null;
        Token                          var_type       = null;
        BitStreamParser.bagName_return bagName7       = null;

        CommonTree                     var_key_tree   = null;
        CommonTree                     var_len_tree   = null;
        CommonTree                     var_type_tree  = null;
        RewriteRuleTokenStream         stream_INT     = new RewriteRuleTokenStream(adaptor, "token INT");
        RewriteRuleTokenStream         stream_ID      = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleSubtreeStream       stream_bagName = new RewriteRuleSubtreeStream(adaptor, "rule bagName");
        try {

            // )? -> ^( KEY_DEF ID INT ID ) )

            // )?
            {
                var_key = (Token) match(input, ID, FOLLOW_ID_in_define325);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(var_key);

                var_len = (Token) match(input, INT, FOLLOW_INT_in_define329);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_INT.add(var_len);

                var_type = (Token) match(input, ID, FOLLOW_ID_in_define333);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(var_type);

                int alt3  = 2;
                int LA3_0 = input.LA(1);

                if ((LA3_0 == 52)) {
                    alt3 = 1;
                }
                switch (alt3) {
                case 1: {
                    pushFollow(FOLLOW_bagName_in_define335);
                    bagName7 = bagName();

                    state._fsp--;
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_bagName.add(bagName7.getTree());

                }
                    break;

                }

                // AST REWRITE
                // elements: ID, INT, ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 83:5: -> ^( KEY_DEF ID INT ID )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_DEF, "KEY_DEF"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_1, stream_INT.nextNode());

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // funcStat | condStat | define | bagStart | bagEnd | whileStat | breakStmt |
    // rewindStmt | nodeStart | nodeEnd |
    // callSyntax );
    public final BitStreamParser.statement_return statement() throws RecognitionException {
        BitStreamParser.statement_return retval = new BitStreamParser.statement_return();
        retval.start = input.LT(1);

        CommonTree                             root_0       = null;

        BitStreamParser.forDescriptStat_return var_stmt     = null;

        BitStreamParser.forStat_return         forStat8     = null;

        BitStreamParser.funcStat_return        funcStat9    = null;

        BitStreamParser.condStat_return        condStat10   = null;

        BitStreamParser.define_return          define11     = null;

        BitStreamParser.bagStart_return        bagStart12   = null;

        BitStreamParser.bagEnd_return          bagEnd13     = null;

        BitStreamParser.whileStat_return       whileStat14  = null;

        BitStreamParser.breakStmt_return       breakStmt15  = null;

        BitStreamParser.rewindStmt_return      rewindStmt16 = null;

        BitStreamParser.nodeStart_return       nodeStart17  = null;

        BitStreamParser.nodeEnd_return         nodeEnd18    = null;

        BitStreamParser.callSyntax_return      callSyntax19 = null;

        try {

            // condStat | define | bagStart | bagEnd | whileStat | breakStmt | rewindStmt |
            // nodeStart | nodeEnd |
            // callSyntax )
            int alt4 = 13;
            switch (input.LA(1)) {
            case FOR: {
                int LA4_1 = input.LA(2);

                if ((LA4_1 == 48)) {
                    int LA4_12 = input.LA(3);

                    if ((LA4_12 == ID)) {
                        int LA4_15 = input.LA(4);

                        if ((LA4_15 == EQ)) {
                            int LA4_16 = input.LA(5);

                            if ((LA4_16 == INT)) {
                                int LA4_17 = input.LA(6);

                                if ((LA4_17 == 53)) {
                                    int LA4_18 = input.LA(7);

                                    if ((LA4_18 == ID)) {
                                        int LA4_19 = input.LA(8);

                                        if ((LA4_19 == LT)) {
                                            int LA4_20 = input.LA(9);

                                            if ((LA4_20 == ID)) {
                                                int LA4_21 = input.LA(10);

                                                if ((LA4_21 == 53)) {
                                                    int LA4_22 = input.LA(11);

                                                    if ((LA4_22 == ID)) {
                                                        int LA4_23 = input.LA(12);

                                                        if ((LA4_23 == PLUS)) {
                                                            int LA4_24 = input.LA(13);

                                                            if ((LA4_24 == PLUS)) {
                                                                int LA4_25 = input.LA(14);

                                                                if ((LA4_25 == 50)) {
                                                                    int LA4_26 = input.LA(15);

                                                                    if ((LA4_26 == LBRACE)) {
                                                                        int LA4_27 = input.LA(16);

                                                                        if ((LA4_27 == DESCRIPTOR)) {
                                                                            alt4 = 1;
                                                                        } else if ((LA4_27 == FOR || LA4_27 == ID || LA4_27 == 54
                                                                                || (LA4_27 >= 58 && LA4_27 <= 63)
                                                                                || (LA4_27 >= 65 && LA4_27 <= 67))) {
                                                                            alt4 = 2;
                                                                        } else {
                                                                            if (state.backtracking > 0) {
                                                                                state.failed = true;
                                                                                return retval;
                                                                            }
                                                                            NoViableAltException nvae = new NoViableAltException("", 4, 27, input);

                                                                            throw nvae;

                                                                        }
                                                                    } else if ((LA4_26 == 52)) {
                                                                        alt4 = 2;
                                                                    } else {
                                                                        if (state.backtracking > 0) {
                                                                            state.failed = true;
                                                                            return retval;
                                                                        }
                                                                        NoViableAltException nvae = new NoViableAltException("", 4, 26, input);

                                                                        throw nvae;

                                                                    }
                                                                } else {
                                                                    if (state.backtracking > 0) {
                                                                        state.failed = true;
                                                                        return retval;
                                                                    }
                                                                    NoViableAltException nvae = new NoViableAltException("", 4, 25, input);

                                                                    throw nvae;

                                                                }
                                                            } else {
                                                                if (state.backtracking > 0) {
                                                                    state.failed = true;
                                                                    return retval;
                                                                }
                                                                NoViableAltException nvae = new NoViableAltException("", 4, 24, input);

                                                                throw nvae;

                                                            }
                                                        } else {
                                                            if (state.backtracking > 0) {
                                                                state.failed = true;
                                                                return retval;
                                                            }
                                                            NoViableAltException nvae = new NoViableAltException("", 4, 23, input);

                                                            throw nvae;

                                                        }
                                                    } else {
                                                        if (state.backtracking > 0) {
                                                            state.failed = true;
                                                            return retval;
                                                        }
                                                        NoViableAltException nvae = new NoViableAltException("", 4, 22, input);

                                                        throw nvae;

                                                    }
                                                } else {
                                                    if (state.backtracking > 0) {
                                                        state.failed = true;
                                                        return retval;
                                                    }
                                                    NoViableAltException nvae = new NoViableAltException("", 4, 21, input);

                                                    throw nvae;

                                                }
                                            } else {
                                                if (state.backtracking > 0) {
                                                    state.failed = true;
                                                    return retval;
                                                }
                                                NoViableAltException nvae = new NoViableAltException("", 4, 20, input);

                                                throw nvae;

                                            }
                                        } else {
                                            if (state.backtracking > 0) {
                                                state.failed = true;
                                                return retval;
                                            }
                                            NoViableAltException nvae = new NoViableAltException("", 4, 19, input);

                                            throw nvae;

                                        }
                                    } else {
                                        if (state.backtracking > 0) {
                                            state.failed = true;
                                            return retval;
                                        }
                                        NoViableAltException nvae = new NoViableAltException("", 4, 18, input);

                                        throw nvae;

                                    }
                                } else {
                                    if (state.backtracking > 0) {
                                        state.failed = true;
                                        return retval;
                                    }
                                    NoViableAltException nvae = new NoViableAltException("", 4, 17, input);

                                    throw nvae;

                                }
                            } else {
                                if (state.backtracking > 0) {
                                    state.failed = true;
                                    return retval;
                                }
                                NoViableAltException nvae = new NoViableAltException("", 4, 16, input);

                                throw nvae;

                            }
                        } else {
                            if (state.backtracking > 0) {
                                state.failed = true;
                                return retval;
                            }
                            NoViableAltException nvae = new NoViableAltException("", 4, 15, input);

                            throw nvae;

                        }
                    } else {
                        if (state.backtracking > 0) {
                            state.failed = true;
                            return retval;
                        }
                        NoViableAltException nvae = new NoViableAltException("", 4, 12, input);

                        throw nvae;

                    }
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 4, 1, input);

                    throw nvae;

                }
            }
                break;
            case ID: {
                int LA4_2 = input.LA(2);

                if ((LA4_2 == 49)) {
                    alt4 = 3;
                } else if ((LA4_2 == INT)) {
                    alt4 = 5;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
            }
                break;
            case 60:
            case 65: {
                alt4 = 4;
            }
                break;
            case 58: {
                alt4 = 6;
            }
                break;
            case 54: {
                alt4 = 7;
            }
                break;
            case 67: {
                alt4 = 8;
            }
                break;
            case 63: {
                alt4 = 9;
            }
                break;
            case 66: {
                alt4 = 10;
            }
                break;
            case 62: {
                alt4 = 11;
            }
                break;
            case 61: {
                alt4 = 12;
            }
                break;
            case 59: {
                alt4 = 13;
            }
                break;
            default:
                if (state.backtracking > 0) {
                    state.failed = true;
                    return retval;
                }
                NoViableAltException nvae = new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
            case 1: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_forDescriptStat_in_statement375);
                var_stmt = forDescriptStat();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, var_stmt.getTree());

            }
                break;
            case 2: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_forStat_in_statement381);
                forStat8 = forStat();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, forStat8.getTree());

            }
                break;
            case 3: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_funcStat_in_statement387);
                funcStat9 = funcStat();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, funcStat9.getTree());

            }
                break;
            case 4: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_condStat_in_statement393);
                condStat10 = condStat();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, condStat10.getTree());

            }
                break;
            case 5: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_define_in_statement399);
                define11 = define();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, define11.getTree());

            }
                break;
            case 6: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_bagStart_in_statement405);
                bagStart12 = bagStart();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, bagStart12.getTree());

            }
                break;
            case 7: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_bagEnd_in_statement411);
                bagEnd13 = bagEnd();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, bagEnd13.getTree());

            }
                break;
            case 8: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_whileStat_in_statement417);
                whileStat14 = whileStat();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, whileStat14.getTree());

            }
                break;
            case 9: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_breakStmt_in_statement423);
                breakStmt15 = breakStmt();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, breakStmt15.getTree());

            }
                break;
            case 10: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_rewindStmt_in_statement429);
                rewindStmt16 = rewindStmt();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, rewindStmt16.getTree());

            }
                break;
            case 11: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_nodeStart_in_statement435);
                nodeStart17 = nodeStart();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, nodeStart17.getTree());

            }
                break;
            case 12: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_nodeEnd_in_statement441);
                nodeEnd18 = nodeEnd();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, nodeEnd18.getTree());

            }
                break;
            case 13: {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_callSyntax_in_statement447);
                callSyntax19 = callSyntax();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, callSyntax19.getTree());

            }
                break;

            }
            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // $var) ;
    public final BitStreamParser.callSyntax_return callSyntax() throws RecognitionException {
        BitStreamParser.callSyntax_return retval = new BitStreamParser.callSyntax_return();
        retval.start = input.LT(1);

        CommonTree             root_0                = null;

        Token                  var                   = null;
        Token                  string_literal20      = null;

        CommonTree             var_tree              = null;
        CommonTree             string_literal20_tree = null;
        RewriteRuleTokenStream stream_59             = new RewriteRuleTokenStream(adaptor, "token 59");
        RewriteRuleTokenStream stream_SYNTAX_NAME    = new RewriteRuleTokenStream(adaptor, "token SYNTAX_NAME");

        try {

            // )

            {
                string_literal20 = (Token) match(input, 59, FOLLOW_59_in_callSyntax465);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_59.add(string_literal20);

                var = (Token) match(input, SYNTAX_NAME, FOLLOW_SYNTAX_NAME_in_callSyntax469);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_SYNTAX_NAME.add(var);

                // AST REWRITE
                // elements: var
                // token labels: var
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleTokenStream   stream_var    = new RewriteRuleTokenStream(adaptor, "token var", var);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 107:3: -> ^( KEY_CALL $var)
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_CALL, "KEY_CALL"), root_1);

                            adaptor.addChild(root_1, stream_var.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // ^( KEY_WHILE KEY_LB logical_expression KEY_RB block ) ;
    public final BitStreamParser.whileStat_return whileStat() throws RecognitionException {
        BitStreamParser.whileStat_return retval = new BitStreamParser.whileStat_return();
        retval.start = input.LT(1);

        CommonTree                                root_0                    = null;

        Token                                     string_literal21          = null;
        Token                                     char_literal22            = null;
        Token                                     char_literal24            = null;
        BitStreamParser.logical_expression_return logical_expression23      = null;

        BitStreamParser.block_return              block25                   = null;

        CommonTree                                string_literal21_tree     = null;
        CommonTree                                char_literal22_tree       = null;
        CommonTree                                char_literal24_tree       = null;
        RewriteRuleTokenStream                    stream_67                 = new RewriteRuleTokenStream(adaptor, "token 67");
        RewriteRuleTokenStream                    stream_48                 = new RewriteRuleTokenStream(adaptor, "token 48");
        RewriteRuleTokenStream                    stream_50                 = new RewriteRuleTokenStream(adaptor, "token 50");
        RewriteRuleSubtreeStream                  stream_logical_expression = new RewriteRuleSubtreeStream(adaptor, "rule logical_expression");
        RewriteRuleSubtreeStream                  stream_block              = new RewriteRuleSubtreeStream(adaptor, "rule block");
        try {

            // KEY_WHILE KEY_LB logical_expression KEY_RB block ) )

            {
                string_literal21 = (Token) match(input, 67, FOLLOW_67_in_whileStat502);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_67.add(string_literal21);

                char_literal22 = (Token) match(input, 48, FOLLOW_48_in_whileStat504);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_48.add(char_literal22);

                pushFollow(FOLLOW_logical_expression_in_whileStat506);
                logical_expression23 = logical_expression();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_logical_expression.add(logical_expression23.getTree());

                char_literal24 = (Token) match(input, 50, FOLLOW_50_in_whileStat508);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_50.add(char_literal24);

                pushFollow(FOLLOW_block_in_whileStat510);
                block25 = block();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_block.add(block25.getTree());

                // AST REWRITE
                // elements: block, logical_expression
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 114:3: -> ^( KEY_WHILE KEY_LB logical_expression KEY_RB block )
                    {

                        // logical_expression KEY_RB block )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_WHILE, "KEY_WHILE"), root_1);

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_LB, "KEY_LB"));

                            adaptor.addChild(root_1, stream_logical_expression.nextTree());

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_RB, "KEY_RB"));

                            adaptor.addChild(root_1, stream_block.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.condStat_return condStat() throws RecognitionException {
        BitStreamParser.condStat_return retval = new BitStreamParser.condStat_return();
        retval.start = input.LT(1);

        CommonTree                        root_0       = null;

        BitStreamParser.ifStat_return     ifStat26     = null;

        BitStreamParser.elseifStat_return elseifStat27 = null;

        BitStreamParser.elseStat_return   elseStat28   = null;

        try {

            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_ifStat_in_condStat543);
                ifStat26 = ifStat();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, ifStat26.getTree());

                loop5: do {
                    int alt5  = 2;
                    int LA5_0 = input.LA(1);

                    if ((LA5_0 == 64)) {
                        int LA5_1 = input.LA(2);

                        if ((LA5_1 == 65)) {
                            alt5 = 1;
                        }

                    }

                    switch (alt5) {
                    case 1: {
                        pushFollow(FOLLOW_elseifStat_in_condStat545);
                        elseifStat27 = elseifStat();

                        state._fsp--;
                        if (state.failed)
                            return retval;
                        if (state.backtracking == 0)
                            adaptor.addChild(root_0, elseifStat27.getTree());

                    }
                        break;

                    default:
                        break loop5;
                    }
                } while (true);

                loop6: do {
                    int alt6  = 2;
                    int LA6_0 = input.LA(1);

                    if ((LA6_0 == 64)) {
                        alt6 = 1;
                    }

                    switch (alt6) {
                    case 1: {
                        pushFollow(FOLLOW_elseStat_in_condStat548);
                        elseStat28 = elseStat();

                        state._fsp--;
                        if (state.failed)
                            return retval;
                        if (state.backtracking == 0)
                            adaptor.addChild(root_0, elseStat28.getTree());

                    }
                        break;

                    default:
                        break loop6;
                    }
                } while (true);

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // block -> ^( KEY_IF KEY_LB logical_expression KEY_RB block ) ;
    public final BitStreamParser.ifStat_return ifStat() throws RecognitionException {
        BitStreamParser.ifStat_return retval = new BitStreamParser.ifStat_return();
        retval.start = input.LT(1);

        CommonTree                                root_0                    = null;

        Token                                     string_literal29          = null;
        Token                                     string_literal30          = null;
        Token                                     char_literal31            = null;
        Token                                     char_literal33            = null;
        BitStreamParser.logical_expression_return logical_expression32      = null;

        BitStreamParser.block_return              block34                   = null;

        CommonTree                                string_literal29_tree     = null;
        CommonTree                                string_literal30_tree     = null;
        CommonTree                                char_literal31_tree       = null;
        CommonTree                                char_literal33_tree       = null;
        RewriteRuleTokenStream                    stream_48                 = new RewriteRuleTokenStream(adaptor, "token 48");
        RewriteRuleTokenStream                    stream_65                 = new RewriteRuleTokenStream(adaptor, "token 65");
        RewriteRuleTokenStream                    stream_60                 = new RewriteRuleTokenStream(adaptor, "token 60");
        RewriteRuleTokenStream                    stream_50                 = new RewriteRuleTokenStream(adaptor, "token 50");
        RewriteRuleSubtreeStream                  stream_logical_expression = new RewriteRuleSubtreeStream(adaptor, "rule logical_expression");
        RewriteRuleSubtreeStream                  stream_block              = new RewriteRuleSubtreeStream(adaptor, "rule block");
        try {

            // block -> ^( KEY_IF KEY_LB logical_expression KEY_RB block ) )

            {

                int alt7  = 2;
                int LA7_0 = input.LA(1);

                if ((LA7_0 == 65)) {
                    alt7 = 1;
                } else if ((LA7_0 == 60)) {
                    alt7 = 2;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    NoViableAltException nvae = new NoViableAltException("", 7, 0, input);

                    throw nvae;

                }
                switch (alt7) {
                case 1: {
                    string_literal29 = (Token) match(input, 65, FOLLOW_65_in_ifStat570);
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_65.add(string_literal29);

                }
                    break;
                case 2: {
                    string_literal30 = (Token) match(input, 60, FOLLOW_60_in_ifStat578);
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_60.add(string_literal30);

                }
                    break;

                }

                char_literal31 = (Token) match(input, 48, FOLLOW_48_in_ifStat586);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_48.add(char_literal31);

                pushFollow(FOLLOW_logical_expression_in_ifStat588);
                logical_expression32 = logical_expression();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_logical_expression.add(logical_expression32.getTree());

                char_literal33 = (Token) match(input, 50, FOLLOW_50_in_ifStat590);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_50.add(char_literal33);

                pushFollow(FOLLOW_block_in_ifStat592);
                block34 = block();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_block.add(block34.getTree());

                // AST REWRITE
                // elements: logical_expression, block
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 130:5: -> ^( KEY_IF KEY_LB logical_expression KEY_RB block )
                    {

                        // KEY_RB block )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_IF, "KEY_IF"), root_1);

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_LB, "KEY_LB"));

                            adaptor.addChild(root_1, stream_logical_expression.nextTree());

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_RB, "KEY_RB"));

                            adaptor.addChild(root_1, stream_block.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.elseStat_return elseStat() throws RecognitionException {
        BitStreamParser.elseStat_return retval = new BitStreamParser.elseStat_return();
        retval.start = input.LT(1);

        CommonTree                   root_0                = null;

        Token                        string_literal35      = null;
        BitStreamParser.block_return block36               = null;

        CommonTree                   string_literal35_tree = null;
        RewriteRuleTokenStream       stream_64             = new RewriteRuleTokenStream(adaptor, "token 64");
        RewriteRuleSubtreeStream     stream_block          = new RewriteRuleSubtreeStream(adaptor, "rule block");
        try {

            {
                string_literal35 = (Token) match(input, 64, FOLLOW_64_in_elseStat631);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_64.add(string_literal35);

                pushFollow(FOLLOW_block_in_elseStat633);
                block36 = block();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_block.add(block36.getTree());

                // AST REWRITE
                // elements: block
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 137:5: -> ^( KEY_ELSE block )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_ELSE, "KEY_ELSE"), root_1);

                            adaptor.addChild(root_1, stream_block.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // block -> ^( KEY_ELSEIF KEY_LB logical_expression KEY_RB block ) ;
    public final BitStreamParser.elseifStat_return elseifStat() throws RecognitionException {
        BitStreamParser.elseifStat_return retval = new BitStreamParser.elseifStat_return();
        retval.start = input.LT(1);

        CommonTree                                root_0                    = null;

        Token                                     string_literal37          = null;
        Token                                     string_literal38          = null;
        Token                                     char_literal39            = null;
        Token                                     char_literal41            = null;
        BitStreamParser.logical_expression_return logical_expression40      = null;

        BitStreamParser.block_return              block42                   = null;

        CommonTree                                string_literal37_tree     = null;
        CommonTree                                string_literal38_tree     = null;
        CommonTree                                char_literal39_tree       = null;
        CommonTree                                char_literal41_tree       = null;
        RewriteRuleTokenStream                    stream_48                 = new RewriteRuleTokenStream(adaptor, "token 48");
        RewriteRuleTokenStream                    stream_64                 = new RewriteRuleTokenStream(adaptor, "token 64");
        RewriteRuleTokenStream                    stream_65                 = new RewriteRuleTokenStream(adaptor, "token 65");
        RewriteRuleTokenStream                    stream_50                 = new RewriteRuleTokenStream(adaptor, "token 50");
        RewriteRuleSubtreeStream                  stream_logical_expression = new RewriteRuleSubtreeStream(adaptor, "rule logical_expression");
        RewriteRuleSubtreeStream                  stream_block              = new RewriteRuleSubtreeStream(adaptor, "rule block");
        try {

            // -> ^( KEY_ELSEIF KEY_LB logical_expression KEY_RB block ) )

            {
                string_literal37 = (Token) match(input, 64, FOLLOW_64_in_elseifStat666);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_64.add(string_literal37);

                string_literal38 = (Token) match(input, 65, FOLLOW_65_in_elseifStat668);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_65.add(string_literal38);

                char_literal39 = (Token) match(input, 48, FOLLOW_48_in_elseifStat670);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_48.add(char_literal39);

                pushFollow(FOLLOW_logical_expression_in_elseifStat672);
                logical_expression40 = logical_expression();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_logical_expression.add(logical_expression40.getTree());

                char_literal41 = (Token) match(input, 50, FOLLOW_50_in_elseifStat674);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_50.add(char_literal41);

                pushFollow(FOLLOW_block_in_elseifStat676);
                block42 = block();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_block.add(block42.getTree());

                // AST REWRITE
                // elements: logical_expression, block
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 144:5: -> ^( KEY_ELSEIF KEY_LB logical_expression KEY_RB block )
                    {

                        // logical_expression KEY_RB block )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_ELSEIF, "KEY_ELSEIF"), root_1);

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_LB, "KEY_LB"));

                            adaptor.addChild(root_1, stream_logical_expression.nextTree());

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_RB, "KEY_RB"));

                            adaptor.addChild(root_1, stream_block.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // '<=' );
    public final BitStreamParser.compare_operator_return compare_operator() throws RecognitionException {
        BitStreamParser.compare_operator_return retval = new BitStreamParser.compare_operator_return();
        retval.start = input.LT(1);

        CommonTree root_0     = null;

        Token      set43      = null;

        CommonTree set43_tree = null;

        try {

            {
                root_0 = (CommonTree) adaptor.nil();

                set43  = (Token) input.LT(1);

                if (input.LA(1) == EQEQ || input.LA(1) == LT || input.LA(1) == 46 || (input.LA(1) >= 55 && input.LA(1) <= 57)) {
                    input.consume();
                    if (state.backtracking == 0)
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(set43));
                    state.errorRecovery = false;
                    state.failed        = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // )* ;
    public final BitStreamParser.logical_expression_return logical_expression() throws RecognitionException {
        BitStreamParser.logical_expression_return retval = new BitStreamParser.logical_expression_return();
        retval.start = input.LT(1);

        CommonTree                          root_0         = null;

        BitStreamParser.logicExpr_return    logicExpr44    = null;

        BitStreamParser.logicCompare_return logicCompare45 = null;

        BitStreamParser.logicExpr_return    logicExpr46    = null;

        try {

            {
                root_0 = (CommonTree) adaptor.nil();

                pushFollow(FOLLOW_logicExpr_in_logical_expression760);
                logicExpr44 = logicExpr();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    adaptor.addChild(root_0, logicExpr44.getTree());

                loop8: do {
                    int alt8  = 2;
                    int LA8_0 = input.LA(1);

                    if ((LA8_0 == 47 || LA8_0 == 68)) {
                        alt8 = 1;
                    }

                    switch (alt8) {
                    case 1: {
                        pushFollow(FOLLOW_logicCompare_in_logical_expression763);
                        logicCompare45 = logicCompare();

                        state._fsp--;
                        if (state.failed)
                            return retval;
                        if (state.backtracking == 0)
                            adaptor.addChild(root_0, logicCompare45.getTree());

                        pushFollow(FOLLOW_logicExpr_in_logical_expression765);
                        logicExpr46 = logicExpr();

                        state._fsp--;
                        if (state.failed)
                            return retval;
                        if (state.backtracking == 0)
                            adaptor.addChild(root_0, logicExpr46.getTree());

                    }
                        break;

                    default:
                        break loop8;
                    }
                } while (true);

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // ^( KEY_LOGIC ID compare_operator $var_value) ;
    public final BitStreamParser.logicExpr_return logicExpr() throws RecognitionException {
        BitStreamParser.logicExpr_return retval = new BitStreamParser.logicExpr_return();
        retval.start = input.LT(1);

        CommonTree                              root_0                  = null;

        Token                                   ID47                    = null;
        BitStreamParser.logicValue_return       var_value               = null;

        BitStreamParser.compare_operator_return compare_operator48      = null;

        CommonTree                              ID47_tree               = null;
        RewriteRuleTokenStream                  stream_ID               = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleSubtreeStream                stream_compare_operator = new RewriteRuleSubtreeStream(adaptor, "rule compare_operator");
        RewriteRuleSubtreeStream                stream_logicValue       = new RewriteRuleSubtreeStream(adaptor, "rule logicValue");
        try {

            // ^( KEY_LOGIC ID compare_operator $var_value) )

            {
                ID47 = (Token) match(input, ID, FOLLOW_ID_in_logicExpr782);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID47);

                pushFollow(FOLLOW_compare_operator_in_logicExpr784);
                compare_operator48 = compare_operator();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_compare_operator.add(compare_operator48.getTree());

                pushFollow(FOLLOW_logicValue_in_logicExpr788);
                var_value = logicValue();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_logicValue.add(var_value.getTree());

                // AST REWRITE
                // elements: compare_operator, ID, var_value
                // token labels:
                // rule labels: retval, var_value
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval    = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);
                    RewriteRuleSubtreeStream stream_var_value = new RewriteRuleSubtreeStream(adaptor, "rule var_value",
                            var_value != null ? var_value.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 166:5: -> ^( KEY_LOGIC ID compare_operator $var_value)
                    {

                        // $var_value)
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_LOGIC, "KEY_LOGIC"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_1, stream_compare_operator.nextTree());

                            adaptor.addChild(root_1, stream_var_value.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.logicValue_return logicValue() throws RecognitionException {
        BitStreamParser.logicValue_return retval = new BitStreamParser.logicValue_return();
        retval.start = input.LT(1);

        CommonTree root_0     = null;

        Token      set49      = null;

        CommonTree set49_tree = null;

        try {

            {
                root_0 = (CommonTree) adaptor.nil();

                set49  = (Token) input.LT(1);

                if (input.LA(1) == HexLiteral || input.LA(1) == INT) {
                    input.consume();
                    if (state.backtracking == 0)
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(set49));
                    state.errorRecovery = false;
                    state.failed        = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.logicCompare_return logicCompare() throws RecognitionException {
        BitStreamParser.logicCompare_return retval = new BitStreamParser.logicCompare_return();
        retval.start = input.LT(1);

        CommonTree root_0     = null;

        Token      set50      = null;

        CommonTree set50_tree = null;

        try {

            {
                root_0 = (CommonTree) adaptor.nil();

                set50  = (Token) input.LT(1);

                if (input.LA(1) == 47 || input.LA(1) == 68) {
                    input.consume();
                    if (state.backtracking == 0)
                        adaptor.addChild(root_0, (CommonTree) adaptor.create(set50));
                    state.errorRecovery = false;
                    state.failed        = false;
                } else {
                    if (state.backtracking > 0) {
                        state.failed = true;
                        return retval;
                    }
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    throw mse;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // )? ) ;
    public final BitStreamParser.funcStat_return funcStat() throws RecognitionException {
        BitStreamParser.funcStat_return retval = new BitStreamParser.funcStat_return();
        retval.start = input.LT(1);

        CommonTree                   root_0                = null;

        Token                        ID51                  = null;
        Token                        string_literal52      = null;
        BitStreamParser.block_return block53               = null;

        CommonTree                   ID51_tree             = null;
        CommonTree                   string_literal52_tree = null;
        RewriteRuleTokenStream       stream_49             = new RewriteRuleTokenStream(adaptor, "token 49");
        RewriteRuleTokenStream       stream_ID             = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleSubtreeStream     stream_block          = new RewriteRuleSubtreeStream(adaptor, "rule block");
        try {

            // ) )

            {
                ID51 = (Token) match(input, ID, FOLLOW_ID_in_funcStat868);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID51);

                string_literal52 = (Token) match(input, 49, FOLLOW_49_in_funcStat870);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_49.add(string_literal52);

                int alt9  = 2;
                int LA9_0 = input.LA(1);

                if ((LA9_0 == LBRACE)) {
                    alt9 = 1;
                }
                switch (alt9) {
                case 1: {
                    pushFollow(FOLLOW_block_in_funcStat872);
                    block53 = block();

                    state._fsp--;
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_block.add(block53.getTree());

                }
                    break;

                }

                // AST REWRITE
                // elements: block, ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 185:5: -> ^( KEY_FCT ID ( block )? )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_FCT, "KEY_FCT"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            if (stream_block.hasNext()) {
                                adaptor.addChild(root_1, stream_block.nextTree());

                            }
                            stream_block.reset();

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.breakStmt_return breakStmt() throws RecognitionException {
        BitStreamParser.breakStmt_return retval = new BitStreamParser.breakStmt_return();
        retval.start = input.LT(1);

        CommonTree             root_0                = null;

        Token                  string_literal54      = null;

        CommonTree             string_literal54_tree = null;
        RewriteRuleTokenStream stream_63             = new RewriteRuleTokenStream(adaptor, "token 63");

        try {

            {
                string_literal54 = (Token) match(input, 63, FOLLOW_63_in_breakStmt908);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_63.add(string_literal54);

                // AST REWRITE
                // elements:
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 191:3: -> ^( KEY_BREAK NEWLINE )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_BREAK, "KEY_BREAK"), root_1);

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(NEWLINE, "NEWLINE"));

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // ^( KEY_REWIND KEY_LB $var_value KEY_RB NEWLINE ) ;
    public final BitStreamParser.rewindStmt_return rewindStmt() throws RecognitionException {
        BitStreamParser.rewindStmt_return retval = new BitStreamParser.rewindStmt_return();
        retval.start = input.LT(1);

        CommonTree                        root_0                = null;

        Token                             string_literal55      = null;
        Token                             char_literal56        = null;
        Token                             char_literal57        = null;
        BitStreamParser.logicValue_return var_value             = null;

        CommonTree                        string_literal55_tree = null;
        CommonTree                        char_literal56_tree   = null;
        CommonTree                        char_literal57_tree   = null;
        RewriteRuleTokenStream            stream_48             = new RewriteRuleTokenStream(adaptor, "token 48");
        RewriteRuleTokenStream            stream_66             = new RewriteRuleTokenStream(adaptor, "token 66");
        RewriteRuleTokenStream            stream_50             = new RewriteRuleTokenStream(adaptor, "token 50");
        RewriteRuleSubtreeStream          stream_logicValue     = new RewriteRuleSubtreeStream(adaptor, "rule logicValue");
        try {

            // KEY_REWIND KEY_LB $var_value KEY_RB NEWLINE ) )

            {
                string_literal55 = (Token) match(input, 66, FOLLOW_66_in_rewindStmt933);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_66.add(string_literal55);

                char_literal56 = (Token) match(input, 48, FOLLOW_48_in_rewindStmt935);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_48.add(char_literal56);

                pushFollow(FOLLOW_logicValue_in_rewindStmt939);
                var_value = logicValue();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_logicValue.add(var_value.getTree());

                char_literal57 = (Token) match(input, 50, FOLLOW_50_in_rewindStmt941);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_50.add(char_literal57);

                // AST REWRITE
                // elements: var_value
                // token labels:
                // rule labels: retval, var_value
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval    = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);
                    RewriteRuleSubtreeStream stream_var_value = new RewriteRuleSubtreeStream(adaptor, "rule var_value",
                            var_value != null ? var_value.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 197:3: -> ^( KEY_REWIND KEY_LB $var_value KEY_RB NEWLINE )
                    {

                        // KEY_RB NEWLINE )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_REWIND, "KEY_REWIND"), root_1);

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_LB, "KEY_LB"));

                            adaptor.addChild(root_1, stream_var_value.nextTree());

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_RB, "KEY_RB"));

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(NEWLINE, "NEWLINE"));

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.bagStart_return bagStart() throws RecognitionException {
        BitStreamParser.bagStart_return retval = new BitStreamParser.bagStart_return();
        retval.start = input.LT(1);

        CommonTree             root_0                = null;

        Token                  string_literal58      = null;
        Token                  ID59                  = null;

        CommonTree             string_literal58_tree = null;
        CommonTree             ID59_tree             = null;
        RewriteRuleTokenStream stream_58             = new RewriteRuleTokenStream(adaptor, "token 58");
        RewriteRuleTokenStream stream_ID             = new RewriteRuleTokenStream(adaptor, "token ID");

        try {

            {
                string_literal58 = (Token) match(input, 58, FOLLOW_58_in_bagStart980);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_58.add(string_literal58);

                ID59 = (Token) match(input, ID, FOLLOW_ID_in_bagStart982);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID59);

                // AST REWRITE
                // elements: ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 204:5: -> ^( BAG_START ID )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(BAG_START, "BAG_START"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.bagEnd_return bagEnd() throws RecognitionException {
        BitStreamParser.bagEnd_return retval = new BitStreamParser.bagEnd_return();
        retval.start = input.LT(1);

        CommonTree             root_0                = null;

        Token                  string_literal60      = null;
        Token                  ID61                  = null;

        CommonTree             string_literal60_tree = null;
        CommonTree             ID61_tree             = null;
        RewriteRuleTokenStream stream_ID             = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleTokenStream stream_54             = new RewriteRuleTokenStream(adaptor, "token 54");

        try {

            {
                string_literal60 = (Token) match(input, 54, FOLLOW_54_in_bagEnd1015);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_54.add(string_literal60);

                ID61 = (Token) match(input, ID, FOLLOW_ID_in_bagEnd1017);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID61);

                // AST REWRITE
                // elements: ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 211:5: -> ^( BAG_END ID )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(BAG_END, "BAG_END"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.bagName_return bagName() throws RecognitionException {
        BitStreamParser.bagName_return retval = new BitStreamParser.bagName_return();
        retval.start = input.LT(1);

        CommonTree             root_0              = null;

        Token                  var                 = null;
        Token                  char_literal62      = null;
        Token                  char_literal63      = null;
        Token                  char_literal64      = null;
        Token                  char_literal65      = null;

        CommonTree             var_tree            = null;
        CommonTree             char_literal62_tree = null;
        CommonTree             char_literal63_tree = null;
        CommonTree             char_literal64_tree = null;
        CommonTree             char_literal65_tree = null;
        RewriteRuleTokenStream stream_51           = new RewriteRuleTokenStream(adaptor, "token 51");
        RewriteRuleTokenStream stream_ID           = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleTokenStream stream_52           = new RewriteRuleTokenStream(adaptor, "token 52");

        try {

            {
                char_literal62 = (Token) match(input, 52, FOLLOW_52_in_bagName1050);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_52.add(char_literal62);

                char_literal63 = (Token) match(input, 51, FOLLOW_51_in_bagName1052);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_51.add(char_literal63);

                var = (Token) match(input, ID, FOLLOW_ID_in_bagName1056);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(var);

                char_literal64 = (Token) match(input, 51, FOLLOW_51_in_bagName1058);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_51.add(char_literal64);

                char_literal65 = (Token) match(input, 52, FOLLOW_52_in_bagName1060);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_52.add(char_literal65);

                // AST REWRITE
                // elements: var
                // token labels: var
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleTokenStream   stream_var    = new RewriteRuleTokenStream(adaptor, "token var", var);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 218:5: -> ^( $var)
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot(stream_var.nextNode(), root_1);

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // '+' ')' -> ^( $end) ;
    public final BitStreamParser.forCond_return forCond() throws RecognitionException {
        BitStreamParser.forCond_return retval = new BitStreamParser.forCond_return();
        retval.start = input.LT(1);

        CommonTree             root_0              = null;

        Token                  end                 = null;
        Token                  char_literal66      = null;
        Token                  ID67                = null;
        Token                  char_literal68      = null;
        Token                  INT69               = null;
        Token                  char_literal70      = null;
        Token                  ID71                = null;
        Token                  char_literal72      = null;
        Token                  char_literal73      = null;
        Token                  ID74                = null;
        Token                  char_literal75      = null;
        Token                  char_literal76      = null;
        Token                  char_literal77      = null;

        CommonTree             end_tree            = null;
        CommonTree             char_literal66_tree = null;
        CommonTree             ID67_tree           = null;
        CommonTree             char_literal68_tree = null;
        CommonTree             INT69_tree          = null;
        CommonTree             char_literal70_tree = null;
        CommonTree             ID71_tree           = null;
        CommonTree             char_literal72_tree = null;
        CommonTree             char_literal73_tree = null;
        CommonTree             ID74_tree           = null;
        CommonTree             char_literal75_tree = null;
        CommonTree             char_literal76_tree = null;
        CommonTree             char_literal77_tree = null;
        RewriteRuleTokenStream stream_48           = new RewriteRuleTokenStream(adaptor, "token 48");
        RewriteRuleTokenStream stream_PLUS         = new RewriteRuleTokenStream(adaptor, "token PLUS");
        RewriteRuleTokenStream stream_LT           = new RewriteRuleTokenStream(adaptor, "token LT");
        RewriteRuleTokenStream stream_INT          = new RewriteRuleTokenStream(adaptor, "token INT");
        RewriteRuleTokenStream stream_EQ           = new RewriteRuleTokenStream(adaptor, "token EQ");
        RewriteRuleTokenStream stream_ID           = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleTokenStream stream_53           = new RewriteRuleTokenStream(adaptor, "token 53");
        RewriteRuleTokenStream stream_50           = new RewriteRuleTokenStream(adaptor, "token 50");

        try {

            // '+' ')' -> ^( $end) )

            // ')'
            {
                char_literal66 = (Token) match(input, 48, FOLLOW_48_in_forCond1092);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_48.add(char_literal66);

                ID67 = (Token) match(input, ID, FOLLOW_ID_in_forCond1094);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID67);

                char_literal68 = (Token) match(input, EQ, FOLLOW_EQ_in_forCond1096);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_EQ.add(char_literal68);

                INT69 = (Token) match(input, INT, FOLLOW_INT_in_forCond1098);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_INT.add(INT69);

                char_literal70 = (Token) match(input, 53, FOLLOW_53_in_forCond1100);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_53.add(char_literal70);

                ID71 = (Token) match(input, ID, FOLLOW_ID_in_forCond1102);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID71);

                char_literal72 = (Token) match(input, LT, FOLLOW_LT_in_forCond1104);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_LT.add(char_literal72);

                end = (Token) match(input, ID, FOLLOW_ID_in_forCond1108);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(end);

                char_literal73 = (Token) match(input, 53, FOLLOW_53_in_forCond1110);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_53.add(char_literal73);

                ID74 = (Token) match(input, ID, FOLLOW_ID_in_forCond1112);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID74);

                char_literal75 = (Token) match(input, PLUS, FOLLOW_PLUS_in_forCond1114);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_PLUS.add(char_literal75);

                char_literal76 = (Token) match(input, PLUS, FOLLOW_PLUS_in_forCond1116);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_PLUS.add(char_literal76);

                char_literal77 = (Token) match(input, 50, FOLLOW_50_in_forCond1118);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_50.add(char_literal77);

                // AST REWRITE
                // elements: end
                // token labels: end
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleTokenStream   stream_end    = new RewriteRuleTokenStream(adaptor, "token end", end);
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 225:5: -> ^( $end)
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot(stream_end.nextNode(), root_1);

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // KEY_FOR_START ( bagName )? NEWLINE KEY_FOR forCond KEY_PUSH block KEY_POP
    // NEWLINE KEY_FOR_END ) ;
    public final BitStreamParser.forStat_return forStat() throws RecognitionException {
        BitStreamParser.forStat_return retval = new BitStreamParser.forStat_return();
        retval.start = input.LT(1);

        CommonTree                     root_0         = null;

        Token                          FOR78          = null;
        BitStreamParser.forCond_return forCond79      = null;

        BitStreamParser.bagName_return bagName80      = null;

        BitStreamParser.block_return   block81        = null;

        CommonTree                     FOR78_tree     = null;
        RewriteRuleTokenStream         stream_FOR     = new RewriteRuleTokenStream(adaptor, "token FOR");
        RewriteRuleSubtreeStream       stream_bagName = new RewriteRuleSubtreeStream(adaptor, "rule bagName");
        RewriteRuleSubtreeStream       stream_block   = new RewriteRuleSubtreeStream(adaptor, "rule block");
        RewriteRuleSubtreeStream       stream_forCond = new RewriteRuleSubtreeStream(adaptor, "rule forCond");
        try {

            // KEY_FOR_START ( bagName )? NEWLINE KEY_FOR forCond KEY_PUSH block KEY_POP
            // NEWLINE KEY_FOR_END ) )

            {
                FOR78 = (Token) match(input, FOR, FOLLOW_FOR_in_forStat1150);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_FOR.add(FOR78);

                pushFollow(FOLLOW_forCond_in_forStat1152);
                forCond79 = forCond();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_forCond.add(forCond79.getTree());

                int alt10  = 2;
                int LA10_0 = input.LA(1);

                if ((LA10_0 == 52)) {
                    alt10 = 1;
                }
                switch (alt10) {
                case 1: {
                    pushFollow(FOLLOW_bagName_in_forStat1154);
                    bagName80 = bagName();

                    state._fsp--;
                    if (state.failed)
                        return retval;
                    if (state.backtracking == 0)
                        stream_bagName.add(bagName80.getTree());

                }
                    break;

                }

                pushFollow(FOLLOW_block_in_forStat1157);
                block81 = block();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_block.add(block81.getTree());

                // AST REWRITE
                // elements: block, bagName, forCond
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 232:5: -> ^( KEY_FOR_START ( bagName )? NEWLINE KEY_FOR forCond KEY_PUSH
                    // block KEY_POP NEWLINE
                    // KEY_FOR_END )
                    {

                        // NEWLINE KEY_FOR forCond KEY_PUSH block KEY_POP NEWLINE KEY_FOR_END )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_FOR_START, "KEY_FOR_START"), root_1);

                            if (stream_bagName.hasNext()) {
                                adaptor.addChild(root_1, stream_bagName.nextTree());

                            }
                            stream_bagName.reset();

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(NEWLINE, "NEWLINE"));

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_FOR, "KEY_FOR"));

                            adaptor.addChild(root_1, stream_forCond.nextTree());

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_PUSH, "KEY_PUSH"));

                            adaptor.addChild(root_1, stream_block.nextTree());

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_POP, "KEY_POP"));

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(NEWLINE, "NEWLINE"));

                            adaptor.addChild(root_1, (CommonTree) adaptor.create(KEY_FOR_END, "KEY_FOR_END"));

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.nodeStart_return nodeStart() throws RecognitionException {
        BitStreamParser.nodeStart_return retval = new BitStreamParser.nodeStart_return();
        retval.start = input.LT(1);

        CommonTree             root_0                = null;

        Token                  string_literal82      = null;
        Token                  ID83                  = null;

        CommonTree             string_literal82_tree = null;
        CommonTree             ID83_tree             = null;
        RewriteRuleTokenStream stream_ID             = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleTokenStream stream_62             = new RewriteRuleTokenStream(adaptor, "token 62");

        try {

            {
                string_literal82 = (Token) match(input, 62, FOLLOW_62_in_nodeStart1208);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_62.add(string_literal82);

                ID83 = (Token) match(input, ID, FOLLOW_ID_in_nodeStart1210);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID83);

                // AST REWRITE
                // elements: ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 238:4: -> ^( KEY_NODE_START ID )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_NODE_START, "KEY_NODE_START"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public final BitStreamParser.nodeEnd_return nodeEnd() throws RecognitionException {
        BitStreamParser.nodeEnd_return retval = new BitStreamParser.nodeEnd_return();
        retval.start = input.LT(1);

        CommonTree             root_0                = null;

        Token                  string_literal84      = null;
        Token                  ID85                  = null;

        CommonTree             string_literal84_tree = null;
        CommonTree             ID85_tree             = null;
        RewriteRuleTokenStream stream_ID             = new RewriteRuleTokenStream(adaptor, "token ID");
        RewriteRuleTokenStream stream_61             = new RewriteRuleTokenStream(adaptor, "token 61");

        try {

            {
                string_literal84 = (Token) match(input, 61, FOLLOW_61_in_nodeEnd1248);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_61.add(string_literal84);

                ID85 = (Token) match(input, ID, FOLLOW_ID_in_nodeEnd1251);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_ID.add(ID85);

                // AST REWRITE
                // elements: ID
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 244:4: -> ^( KEY_NODE_END ID )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_NODE_END, "KEY_NODE_END"), root_1);

                            adaptor.addChild(root_1, stream_ID.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // KEY_FOR_DESCRIPTOR forCond DESCRIPTOR ) ;
    public final BitStreamParser.forDescriptStat_return forDescriptStat() throws RecognitionException {
        BitStreamParser.forDescriptStat_return retval = new BitStreamParser.forDescriptStat_return();
        retval.start = input.LT(1);

        CommonTree                     root_0              = null;

        Token                          FOR86               = null;
        Token                          char_literal88      = null;
        Token                          DESCRIPTOR89        = null;
        Token                          char_literal90      = null;
        BitStreamParser.forCond_return forCond87           = null;

        CommonTree                     FOR86_tree          = null;
        CommonTree                     char_literal88_tree = null;
        CommonTree                     DESCRIPTOR89_tree   = null;
        CommonTree                     char_literal90_tree = null;
        RewriteRuleTokenStream         stream_DESCRIPTOR   = new RewriteRuleTokenStream(adaptor, "token DESCRIPTOR");
        RewriteRuleTokenStream         stream_FOR          = new RewriteRuleTokenStream(adaptor, "token FOR");
        RewriteRuleTokenStream         stream_RBRACE       = new RewriteRuleTokenStream(adaptor, "token RBRACE");
        RewriteRuleTokenStream         stream_LBRACE       = new RewriteRuleTokenStream(adaptor, "token LBRACE");
        RewriteRuleSubtreeStream       stream_forCond      = new RewriteRuleSubtreeStream(adaptor, "rule forCond");
        try {

            // KEY_FOR_DESCRIPTOR forCond DESCRIPTOR ) )

            {
                FOR86 = (Token) match(input, FOR, FOLLOW_FOR_in_forDescriptStat1286);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_FOR.add(FOR86);

                pushFollow(FOLLOW_forCond_in_forDescriptStat1288);
                forCond87 = forCond();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_forCond.add(forCond87.getTree());

                char_literal88 = (Token) match(input, LBRACE, FOLLOW_LBRACE_in_forDescriptStat1290);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_LBRACE.add(char_literal88);

                DESCRIPTOR89 = (Token) match(input, DESCRIPTOR, FOLLOW_DESCRIPTOR_in_forDescriptStat1292);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_DESCRIPTOR.add(DESCRIPTOR89);

                char_literal90 = (Token) match(input, RBRACE, FOLLOW_RBRACE_in_forDescriptStat1294);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_RBRACE.add(char_literal90);

                // AST REWRITE
                // elements: DESCRIPTOR, forCond
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 251:5: -> ^( KEY_FOR_DESCRIPTOR forCond DESCRIPTOR )
                    {

                        // DESCRIPTOR )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_FOR_DESCRIPTOR, "KEY_FOR_DESCRIPTOR"), root_1);

                            adaptor.addChild(root_1, stream_forCond.nextTree());

                            adaptor.addChild(root_1, stream_DESCRIPTOR.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // KEY_FOR_BUFFER forCond ) ;
    public final BitStreamParser.forBufferStat_return forBufferStat() throws RecognitionException {
        BitStreamParser.forBufferStat_return retval = new BitStreamParser.forBufferStat_return();
        retval.start = input.LT(1);

        CommonTree                     root_0              = null;

        Token                          FOR91               = null;
        Token                          char_literal93      = null;
        Token                          wildcard94          = null;
        Token                          char_literal95      = null;
        BitStreamParser.forCond_return forCond92           = null;

        CommonTree                     FOR91_tree          = null;
        CommonTree                     char_literal93_tree = null;
        CommonTree                     wildcard94_tree     = null;
        CommonTree                     char_literal95_tree = null;
        RewriteRuleTokenStream         stream_FOR          = new RewriteRuleTokenStream(adaptor, "token FOR");
        RewriteRuleTokenStream         stream_RBRACE       = new RewriteRuleTokenStream(adaptor, "token RBRACE");
        RewriteRuleTokenStream         stream_LBRACE       = new RewriteRuleTokenStream(adaptor, "token LBRACE");
        RewriteRuleSubtreeStream       stream_forCond      = new RewriteRuleSubtreeStream(adaptor, "rule forCond");
        try {

            // KEY_FOR_BUFFER forCond ) )

            {
                FOR91 = (Token) match(input, FOR, FOLLOW_FOR_in_forBufferStat1329);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_FOR.add(FOR91);

                pushFollow(FOLLOW_forCond_in_forBufferStat1331);
                forCond92 = forCond();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_forCond.add(forCond92.getTree());

                char_literal93 = (Token) match(input, LBRACE, FOLLOW_LBRACE_in_forBufferStat1333);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_LBRACE.add(char_literal93);

                loop11: do {
                    int alt11  = 2;
                    int LA11_0 = input.LA(1);

                    if ((LA11_0 == RBRACE)) {
                        int LA11_1 = input.LA(2);

                        if (((LA11_1 >= BAG_END && LA11_1 <= 68))) {
                            alt11 = 1;
                        }

                    } else if (((LA11_0 >= BAG_END && LA11_0 <= PROG) || (LA11_0 >= SYNTAX_NAME && LA11_0 <= 68))) {
                        alt11 = 1;
                    }

                    switch (alt11) {
                    case 1: {
                        wildcard94 = (Token) input.LT(1);

                        matchAny(input);
                        if (state.failed)
                            return retval;
                        if (state.backtracking == 0) {
                            wildcard94_tree = (CommonTree) adaptor.create(wildcard94);
                            adaptor.addChild(root_0, wildcard94_tree);
                        }

                    }
                        break;

                    default:
                        break loop11;
                    }
                } while (true);

                char_literal95 = (Token) match(input, RBRACE, FOLLOW_RBRACE_in_forBufferStat1338);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_RBRACE.add(char_literal95);

                // AST REWRITE
                // elements: forCond
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 258:5: -> ^( KEY_FOR_BUFFER forCond )
                    {

                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(KEY_FOR_BUFFER, "KEY_FOR_BUFFER"), root_1);

                            adaptor.addChild(root_1, stream_forCond.nextTree());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    // BLK[$var_block,\"\"] LBRACE body RBRACE ) ;
    public final BitStreamParser.block_return block() throws RecognitionException {
        BitStreamParser.block_return retval = new BitStreamParser.block_return();
        retval.start = input.LT(1);

        CommonTree                  root_0         = null;

        Token                       var_block      = null;
        Token                       RBRACE97       = null;
        BitStreamParser.body_return body96         = null;

        CommonTree                  var_block_tree = null;
        CommonTree                  RBRACE97_tree  = null;
        RewriteRuleTokenStream      stream_RBRACE  = new RewriteRuleTokenStream(adaptor, "token RBRACE");
        RewriteRuleTokenStream      stream_LBRACE  = new RewriteRuleTokenStream(adaptor, "token LBRACE");
        RewriteRuleSubtreeStream    stream_body    = new RewriteRuleSubtreeStream(adaptor, "rule body");
        try {

            // BLK[$var_block,\"\"] LBRACE body RBRACE ) )

            {
                var_block = (Token) match(input, LBRACE, FOLLOW_LBRACE_in_block1373);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_LBRACE.add(var_block);

                pushFollow(FOLLOW_body_in_block1375);
                body96 = body();

                state._fsp--;
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_body.add(body96.getTree());

                RBRACE97 = (Token) match(input, RBRACE, FOLLOW_RBRACE_in_block1377);
                if (state.failed)
                    return retval;
                if (state.backtracking == 0)
                    stream_RBRACE.add(RBRACE97);

                // AST REWRITE
                // elements: RBRACE, LBRACE, body
                // token labels:
                // rule labels: retval
                // token list labels:
                // rule list labels:
                // wildcard labels:
                if (state.backtracking == 0) {

                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval = new RewriteRuleSubtreeStream(adaptor, "rule retval",
                            retval != null ? retval.tree : null);

                    root_0 = (CommonTree) adaptor.nil();
                    // 265:5: -> ^( BLK[$var_block,\"\"] LBRACE body RBRACE )
                    {

                        // RBRACE )
                        {
                            CommonTree root_1 = (CommonTree) adaptor.nil();
                            root_1 = (CommonTree) adaptor.becomeRoot((CommonTree) adaptor.create(BLK, var_block, ""), root_1);

                            adaptor.addChild(root_1, stream_LBRACE.nextNode());

                            adaptor.addChild(root_1, stream_body.nextTree());

                            adaptor.addChild(root_1, stream_RBRACE.nextNode());

                            adaptor.addChild(root_0, root_1);
                        }

                    }

                    retval.tree = root_0;
                }

            }

            retval.stop = input.LT(-1);

            if (state.backtracking == 0) {

                retval.tree = (CommonTree) adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        } catch (RecognitionException re) {
            reportError(re);
            recover(input, re);
            retval.tree = (CommonTree) adaptor.errorNode(input, retval.start, input.LT(-1), re);

        } finally {
            // do for sure before leaving
        }
        return retval;
    }

    public static class identiy_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class program_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class body_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class define_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class statement_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class callSyntax_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class whileStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class condStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class ifStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class elseStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class elseifStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class compare_operator_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class logical_expression_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class logicExpr_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class logicValue_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class logicCompare_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class funcStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class breakStmt_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class rewindStmt_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class bagStart_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class bagEnd_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class bagName_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class forCond_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class forStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class nodeStart_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class nodeEnd_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class forDescriptStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class forBufferStat_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

    public static class block_return extends ParserRuleReturnScope {
        CommonTree tree;

        public Object getTree() {
            return tree;
        }
    }

}