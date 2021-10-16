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

import org.antlr.runtime.*;

@SuppressWarnings({ "all", "warnings", "unchecked" })
public class BitStreamLexer extends Lexer {
    public static final int EOF                = -1;
    public static final int T__46              = 46;
    public static final int T__47              = 47;
    public static final int T__48              = 48;
    public static final int T__49              = 49;
    public static final int T__50              = 50;
    public static final int T__51              = 51;
    public static final int T__52              = 52;
    public static final int T__53              = 53;
    public static final int T__54              = 54;
    public static final int T__55              = 55;
    public static final int T__56              = 56;
    public static final int T__57              = 57;
    public static final int T__58              = 58;
    public static final int T__59              = 59;
    public static final int T__60              = 60;
    public static final int T__61              = 61;
    public static final int T__62              = 62;
    public static final int T__63              = 63;
    public static final int T__64              = 64;
    public static final int T__65              = 65;
    public static final int T__66              = 66;
    public static final int T__67              = 67;
    public static final int T__68              = 68;
    public static final int BAG_END            = 4;
    public static final int BAG_START          = 5;
    public static final int BLK                = 6;
    public static final int DESCRIPTOR         = 7;
    public static final int EQ                 = 8;
    public static final int EQEQ               = 9;
    public static final int FOR                = 10;
    public static final int HexDigit           = 11;
    public static final int HexLiteral         = 12;
    public static final int ID                 = 13;
    public static final int INT                = 14;
    public static final int KEY_BREAK          = 15;
    public static final int KEY_CALL           = 16;
    public static final int KEY_DEF            = 17;
    public static final int KEY_ELSE           = 18;
    public static final int KEY_ELSEIF         = 19;
    public static final int KEY_FCT            = 20;
    public static final int KEY_FOR            = 21;
    public static final int KEY_FOR_BUFFER     = 22;
    public static final int KEY_FOR_DESCRIPTOR = 23;
    public static final int KEY_FOR_END        = 24;
    public static final int KEY_FOR_START      = 25;
    public static final int KEY_IDENT          = 26;
    public static final int KEY_IF             = 27;
    public static final int KEY_LB             = 28;
    public static final int KEY_LOGIC          = 29;
    public static final int KEY_NODE_END       = 30;
    public static final int KEY_NODE_START     = 31;
    public static final int KEY_POP            = 32;
    public static final int KEY_PUSH           = 33;
    public static final int KEY_RB             = 34;
    public static final int KEY_REWIND         = 35;
    public static final int KEY_WHILE          = 36;
    public static final int LBRACE             = 37;
    public static final int LT                 = 38;
    public static final int NEWLINE            = 39;
    public static final int NUMBER             = 40;
    public static final int PLUS               = 41;
    public static final int PROG               = 42;
    public static final int RBRACE             = 43;
    public static final int SYNTAX_NAME        = 44;
    public static final int WS                 = 45;
    static final String     DFA8_eotS          = "\1\30\2\uffff\1\43\4\uffff\1\46\1\51\10\54\1\uffff\1\54\1\31\3\54"
            + "\2\uffff\1\72\3\uffff\1\40\1\73\12\uffff\2\54\1\uffff\1\54\1\75" + "\3\54\1\101\3\54\1\uffff\2\54\3\uffff\1\54\1\uffff\3\54\1\uffff"
            + "\3\54\2\116\3\54\1\122\3\54\2\uffff\1\54\1\130\1\uffff\1\54\1\132" + "\3\54\1\uffff\1\136\1\uffff\3\54\1\uffff\1\54\1\143\2\54\1\uffff"
            + "\2\54\1\150\1\54\2\uffff";
    static final String     DFA8_eofS          = "\152\uffff";
    static final String     DFA8_minS          = "\1\11\2\uffff\1\51\4\uffff\1\74\1\75\10\55\1\uffff\1\55\1\130\3"
            + "\55\2\uffff\1\75\3\uffff\1\12\1\11\12\uffff\2\55\1\uffff\11\55\1"
            + "\uffff\2\55\3\uffff\1\55\1\uffff\3\55\1\uffff\5\55\1\40\6\55\2\uffff"
            + "\2\55\1\uffff\5\55\1\uffff\1\55\1\uffff\3\55\1\uffff\4\55\1\uffff" + "\3\55\1\50\2\uffff";
    static final String     DFA8_maxS          = "\1\175\2\uffff\1\60\4\uffff\1\75\1\76\10\172\1\uffff\1\172\1\170"
            + "\3\172\2\uffff\1\75\3\uffff\1\12\1\40\12\uffff\2\172\1\uffff\11" + "\172\1\uffff\2\172\3\uffff\1\172\1\uffff\3\172\1\uffff\14\172\2"
            + "\uffff\2\172\1\uffff\5\172\1\uffff\1\172\1\uffff\3\172\1\uffff\4" + "\172\1\uffff\4\172\2\uffff";
    static final String     DFA8_acceptS       = "\1\uffff\1\1\1\2\1\uffff\1\5\1\6\1\7\1\10\12\uffff\1\27\5\uffff"
            + "\1\34\1\36\1\uffff\1\42\1\43\1\44\2\uffff\1\46\1\4\1\35\1\3\1\11" + "\1\12\1\41\1\14\1\15\1\13\2\uffff\1\33\11\uffff\1\31\2\uffff\1\40"
            + "\1\37\1\45\1\uffff\1\17\3\uffff\1\24\14\uffff\1\32\1\16\2\uffff" + "\1\23\5\uffff\1\22\1\uffff\1\26\3\uffff\1\25\4\uffff\1\20\4\uffff"
            + "\1\21\1\30";
    // $ANTLR end "T__46"
    static final String     DFA8_specialS      = "\152\uffff}>";
    // $ANTLR end "T__47"
    static final String[]   DFA8_transitionS   = {
            "\1\40\1\37\2\uffff\1\36\22\uffff\1\40\1\1\4\uffff\1\2\1\uffff" + "\1\3\1\4\1\5\1\33\1\uffff\1\31\1\uffff\1\6\1\24\11\31\1\uffff"
                    + "\1\7\1\10\1\32\1\11\2\uffff\2\27\1\12\2\27\1\26\2\27\1\13\4" + "\27\1\14\14\27\4\uffff\1\27\1\uffff\1\27\1\15\1\27\1\23\1\16"
                    + "\1\25\2\27\1\17\10\27\1\20\4\27\1\21\3\27\1\34\1\22\1\35",
            "", "", "\1\41\6\uffff\1\42", "", "", "", "", "\1\44\1\45", "\1\47\1\50",
            "\1\55\1\30\1\uffff\12\55\7\uffff\1\52\31\53\4\uffff\1\53\1" + "\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\5\53\1\56\24\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\16\53\1\57\13\53\4\uffff\1" + "\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\21\53\1\60\10\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\13\53\1\61\16\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\5\53\1\62\24\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\4\53\1\63\25\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\7\53\1\64\22\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\4\53\1\65\25\53", "\1\66\37\uffff\1\66",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\16\53\1\67\13\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\16\53\1\70\13\53\4\uffff\1" + "\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53", "", "", "\1\71", "", "", "", "\1\37",
            "\2\40\2\uffff\1\40\22\uffff\1\40", "", "", "", "", "", "", "", "", "", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\13\53\1\74\16\53\4\uffff\1" + "\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\3\53\1\76\26\53\4\uffff\1" + "\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\4\53\1\77\25\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\22\53\1\100\7\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\26\53\1\102\3\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\10\53\1\103\21\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\22\53\1\104\7\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\21\53\1\105\10\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\21\53\1\106\10\53\4\uffff" + "\1\53\1\uffff\32\53", "", "", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\13\53\1\107\16\53\4\uffff" + "\1\53\1\uffff\32\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\4\53\1\110\25\53\4\uffff\1" + "\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\1\111\31\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\4\53\1\112\25\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\10\53\1\113\21\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\13\53\1\114\16\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\2\53\1\115\27\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\117\14\uffff\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff" + "\1\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\120\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\12\53\1\121\17\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\15\53\1\123\14\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\4\53\1\124\25\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\21\53\1\125\10\53", "", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\4\53\1\126\15\53\1\127\7\53" + "\4\uffff\1\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\3\53\1\131\26\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\10\53\1\133\21\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\15\53\1\134\14\53\4\uffff" + "\1\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\23\53\1\135\6\53\4\uffff\1" + "\53\1\uffff\32\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\17\53\1\137\12\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\3\53\1\140\26\53\4\uffff\1" + "\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\1\141\31\53\4\uffff\1\53\1" + "\uffff\32\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\23\53\1\142\6\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\21\53\1\144\10\53\4\uffff" + "\1\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\16\53\1\145\13\53", "",
            "\1\55\1\30\1\uffff\12\55\7\uffff\23\53\1\146\6\53\4\uffff\1" + "\53\1\uffff\32\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\21\53\1\147\10\53",
            "\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff\1\53\1\uffff" + "\32\53",
            "\1\151\4\uffff\1\55\1\30\1\uffff\12\55\7\uffff\32\53\4\uffff" + "\1\53\1\uffff\32\53", "", "" };
    // $ANTLR end "T__48"
    static final short[]    DFA8_eot           = DFA.unpackEncodedString(DFA8_eotS);
    // $ANTLR end "T__49"
    static final short[]    DFA8_eof           = DFA.unpackEncodedString(DFA8_eofS);
    // $ANTLR end "T__50"
    static final char[]     DFA8_min           = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    // $ANTLR end "T__51"
    static final char[]     DFA8_max           = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    // $ANTLR end "T__52"
    static final short[]    DFA8_accept        = DFA.unpackEncodedString(DFA8_acceptS);
    // $ANTLR end "T__53"
    static final short[]    DFA8_special       = DFA.unpackEncodedString(DFA8_specialS);
    // $ANTLR end "T__54"
    static final short[][]  DFA8_transition;
    // $ANTLR end "T__55"

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i = 0; i < numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }
    // $ANTLR end "T__56"

    protected DFA8 dfa8 = new DFA8(this);
    // $ANTLR end "T__57"

    public BitStreamLexer() {
    }
    // $ANTLR end "T__58"

    public BitStreamLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    // $ANTLR end "T__59"

    public BitStreamLexer(CharStream input, RecognizerSharedState state) {
        super(input, state);
    }
    // $ANTLR end "T__60"

    // delegates
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type    = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("!=");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type    = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("&&");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type    = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('(');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type    = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("()");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type    = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match(')');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type    = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('*');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type    = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('/');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type    = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match(';');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "DESCRIPTOR"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type    = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("<<<");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "HexDigit"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type    = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("<=");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "HexLiteral"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type    = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('>');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "FOR"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type    = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match(">=");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "ID"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type    = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match(">>>");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "SYNTAX_NAME"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type    = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("CALL ");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type    = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("If");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type    = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("NODE_END");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "EQ"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type    = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("NODE_START");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "EQEQ"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type    = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("break");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LT"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type    = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("else");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type    = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("if");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type    = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("rewind");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type    = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("while");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "NEWLINE"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type    = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("||");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }
    // $ANTLR end "WS"

    // $ANTLR start "DESCRIPTOR"
    public final void mDESCRIPTOR() throws RecognitionException {
        try {
            int _type    = DESCRIPTOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("descriptor()");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "HexDigit"
    public final void mHexDigit() throws RecognitionException {
        try {

            // )

            {
                if (input.LA(1) == '-' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F')
                        || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }

            }

        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "HexLiteral"
    public final void mHexLiteral() throws RecognitionException {
        try {
            int _type    = HexLiteral;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('0');

                if (input.LA(1) == 'X' || input.LA(1) == 'x') {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }

                int cnt1 = 0;
                loop1: do {
                    int alt1  = 2;
                    int LA1_0 = input.LA(1);

                    if ((LA1_0 == '-' || (LA1_0 >= '0' && LA1_0 <= '9') || (LA1_0 >= 'A' && LA1_0 <= 'F') || (LA1_0 >= 'a' && LA1_0 <= 'f'))) {
                        alt1 = 1;
                    }

                    switch (alt1) {
                    case 1: {
                        if (input.LA(1) == '-' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'F')
                                || (input.LA(1) >= 'a' && input.LA(1) <= 'f')) {
                            input.consume();
                        } else {
                            MismatchedSetException mse = new MismatchedSetException(null, input);
                            recover(mse);
                            throw mse;
                        }

                    }
                        break;

                    default:
                        if (cnt1 >= 1)
                            break loop1;
                        EarlyExitException eee = new EarlyExitException(1, input);
                        throw eee;
                    }
                    cnt1++;
                } while (true);

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "FOR"
    public final void mFOR() throws RecognitionException {
        try {
            int _type    = FOR;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            int alt2     = 2;
            int LA2_0    = input.LA(1);

            if ((LA2_0 == 'f')) {
                alt2 = 1;
            } else if ((LA2_0 == 'F')) {
                alt2 = 2;
            } else {
                NoViableAltException nvae = new NoViableAltException("", 2, 0, input);

                throw nvae;

            }
            switch (alt2) {
            case 1: {
                match("for");

            }
                break;
            case 2: {
                match("FOR");

            }
                break;

            }
            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "ID"
    public final void mID() throws RecognitionException {
        try {
            int _type    = ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            int var_id;

            // .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '-' )* )

            // .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' | '-' )*
            {
                var_id = input.LA(1);

                if ((input.LA(1) >= 'A' && input.LA(1) <= 'Z') || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                    input.consume();
                } else {
                    MismatchedSetException mse = new MismatchedSetException(null, input);
                    recover(mse);
                    throw mse;
                }

                // | '-' )*
                loop3: do {
                    int alt3  = 2;
                    int LA3_0 = input.LA(1);

                    if ((LA3_0 == '-' || (LA3_0 >= '0' && LA3_0 <= '9') || (LA3_0 >= 'A' && LA3_0 <= 'Z') || LA3_0 == '_'
                            || (LA3_0 >= 'a' && LA3_0 <= 'z'))) {
                        alt3 = 1;
                    }

                    switch (alt3) {
                    case 1: {
                        if (input.LA(1) == '-' || (input.LA(1) >= '0' && input.LA(1) <= '9') || (input.LA(1) >= 'A' && input.LA(1) <= 'Z')
                                || input.LA(1) == '_' || (input.LA(1) >= 'a' && input.LA(1) <= 'z')) {
                            input.consume();
                        } else {
                            MismatchedSetException mse = new MismatchedSetException(null, input);
                            recover(mse);
                            throw mse;
                        }

                    }
                        break;

                    default:
                        break loop3;
                    }
                } while (true);

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "SYNTAX_NAME"
    public final void mSYNTAX_NAME() throws RecognitionException {
        try {
            int _type    = SYNTAX_NAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {

                loop4: do {
                    int alt4  = 3;
                    int LA4_0 = input.LA(1);

                    if (((LA4_0 >= 'A' && LA4_0 <= 'Z') || LA4_0 == '_' || (LA4_0 >= 'a' && LA4_0 <= 'z'))) {
                        alt4 = 1;
                    } else if ((LA4_0 == '.')) {
                        alt4 = 2;
                    }

                    switch (alt4) {
                    case 1: {
                        mID();

                    }
                        break;
                    case 2: {
                        match('.');

                    }
                        break;

                    default:
                        break loop4;
                    }
                } while (true);

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type    = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("(0x)");

                mINT();

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type    = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            int var_int;

            {

                int cnt5 = 0;
                loop5: do {
                    int alt5  = 2;
                    int LA5_0 = input.LA(1);

                    if ((LA5_0 == '-' || (LA5_0 >= '0' && LA5_0 <= '9'))) {
                        alt5 = 1;
                    }

                    switch (alt5) {
                    case 1: {
                        var_int = input.LA(1);

                        if (input.LA(1) == '-' || (input.LA(1) >= '0' && input.LA(1) <= '9')) {
                            input.consume();
                        } else {
                            MismatchedSetException mse = new MismatchedSetException(null, input);
                            recover(mse);
                            throw mse;
                        }

                    }
                        break;

                    default:
                        if (cnt5 >= 1)
                            break loop5;
                        EarlyExitException eee = new EarlyExitException(5, input);
                        throw eee;
                    }
                    cnt5++;
                } while (true);

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "EQ"
    public final void mEQ() throws RecognitionException {
        try {
            int _type    = EQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('=');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "EQEQ"
    public final void mEQEQ() throws RecognitionException {
        try {
            int _type    = EQEQ;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match("==");

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "LT"
    public final void mLT() throws RecognitionException {
        try {
            int _type    = LT;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('<');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type    = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('+');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type    = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('{');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type    = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {
                match('}');

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "NEWLINE"
    public final void mNEWLINE() throws RecognitionException {
        try {
            int _type    = NEWLINE;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {

                int alt6  = 2;
                int LA6_0 = input.LA(1);

                if ((LA6_0 == '\r')) {
                    alt6 = 1;
                }
                switch (alt6) {
                case 1: {
                    match('\r');

                }
                    break;

                }

                match('\n');

                _channel = HIDDEN;

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    // $ANTLR start "WS"
    public final void mWS() throws RecognitionException {
        try {
            int _type    = WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;

            {

                int cnt7 = 0;
                loop7: do {
                    int alt7  = 2;
                    int LA7_0 = input.LA(1);

                    if (((LA7_0 >= '\t' && LA7_0 <= '\n') || LA7_0 == '\r' || LA7_0 == ' ')) {
                        alt7 = 1;
                    }

                    switch (alt7) {
                    case 1: {
                        if ((input.LA(1) >= '\t' && input.LA(1) <= '\n') || input.LA(1) == '\r' || input.LA(1) == ' ') {
                            input.consume();
                        } else {
                            MismatchedSetException mse = new MismatchedSetException(null, input);
                            recover(mse);
                            throw mse;
                        }

                    }
                        break;

                    default:
                        if (cnt7 >= 1)
                            break loop7;
                        EarlyExitException eee = new EarlyExitException(7, input);
                        throw eee;
                    }
                    cnt7++;
                } while (true);

                _channel = HIDDEN;

            }

            state.type    = _type;
            state.channel = _channel;
        } finally {
            // do for sure before leaving
        }
    }

    public void mTokens() throws RecognitionException {

        // T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61
        // | T__62 | T__63 | T__64 | T__65
        // | T__66 | T__67 |
        // T__68 | DESCRIPTOR | HexLiteral | FOR | ID | SYNTAX_NAME | NUMBER | INT | EQ
        // | EQEQ | LT | PLUS | LBRACE |
        // RBRACE | NEWLINE | WS
        // )
        int alt8 = 38;
        alt8 = dfa8.predict(input);
        switch (alt8) {
        case 1: {
            mT__46();

        }
            break;
        case 2: {
            mT__47();

        }
            break;
        case 3: {
            mT__48();

        }
            break;
        case 4: {
            mT__49();

        }
            break;
        case 5: {
            mT__50();

        }
            break;
        case 6: {
            mT__51();

        }
            break;
        case 7: {
            mT__52();

        }
            break;
        case 8: {
            mT__53();

        }
            break;
        case 9: {
            mT__54();

        }
            break;
        case 10: {
            mT__55();

        }
            break;
        case 11: {
            mT__56();

        }
            break;
        case 12: {
            mT__57();

        }
            break;
        case 13: {
            mT__58();

        }
            break;
        case 14: {
            mT__59();

        }
            break;
        case 15: {
            mT__60();

        }
            break;
        case 16: {
            mT__61();

        }
            break;
        case 17: {
            mT__62();

        }
            break;
        case 18: {
            mT__63();

        }
            break;
        case 19: {
            mT__64();

        }
            break;
        case 20: {
            mT__65();

        }
            break;
        case 21: {
            mT__66();

        }
            break;
        case 22: {
            mT__67();

        }
            break;
        case 23: {
            mT__68();

        }
            break;
        case 24: {
            mDESCRIPTOR();

        }
            break;
        case 25: {
            mHexLiteral();

        }
            break;
        case 26: {
            mFOR();

        }
            break;
        case 27: {
            mID();

        }
            break;
        case 28: {
            mSYNTAX_NAME();

        }
            break;
        case 29: {
            mNUMBER();

        }
            break;
        case 30: {
            mINT();

        }
            break;
        case 31: {
            mEQ();

        }
            break;
        case 32: {
            mEQEQ();

        }
            break;
        case 33: {
            mLT();

        }
            break;
        case 34: {
            mPLUS();

        }
            break;
        case 35: {
            mLBRACE();

        }
            break;
        case 36: {
            mRBRACE();

        }
            break;
        case 37: {
            mNEWLINE();

        }
            break;
        case 38: {
            mWS();

        }
            break;

        }

    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer     = recognizer;
            this.decisionNumber = 8;
            this.eot            = DFA8_eot;
            this.eof            = DFA8_eof;
            this.min            = DFA8_min;
            this.max            = DFA8_max;
            this.accept         = DFA8_accept;
            this.special        = DFA8_special;
            this.transition     = DFA8_transition;
        }

        public String getDescription() {
            return "1:1: Tokens : ( T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | DESCRIPTOR | HexLiteral | FOR | ID | SYNTAX_NAME | NUMBER | INT | EQ | EQEQ | LT | PLUS | LBRACE | RBRACE | NEWLINE | WS );";
        }
    }

}