NumericLiteral			=	{DecimalLiteral} |
							{HexIntegerLiteral} |
							{OctalIntegerLiteral}

DecimalLiteral			=	{DecimalIntegerLiteral}(\.{DecimalDigits})?({ExponentPart})? |
							\.{DecimalDigits}({ExponentPart})?

DecimalIntegerLiteral	=	0 | [1-9][0-9]*

DecimalDigits 			=	[0-9]+

SignedInteger			=	([+-])? {DecimalDigits}

ExponentPart			=	[eE] {SignedInteger}

HexDigit				=	[0-9a-fA-F]

HexIntegerLiteral		=	(0x|0X) {HexDigit}+

OctalDigit				=	[0-7]

OctalIntegerLiteral		=	0 {OctalDigit}+
