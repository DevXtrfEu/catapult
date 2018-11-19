package eu.xtrf.cat.parser.impl;

import eu.xtrf.cat.CATAnalysis;
import eu.xtrf.cat.CATTool;
import eu.xtrf.cat.MatchType;
import eu.xtrf.cat.Unit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

import static eu.xtrf.cat.MatchType.*;
import static eu.xtrf.cat.Unit.*;

@Test
public class CATAnalysisParserSDLTMS2011XLSXTest extends CATAnalysisParserTest<CATAnalysisParserSDLTMSXLSX> {

	public CATAnalysisParserSDLTMS2011XLSXTest() {
		super(CATAnalysisParserSDLTMSXLSX.class);
	}
	
	@DataProvider(name="files")
	public Object[][] getFiles() {
		CATAnalysis result1 = new CATAnalysis(CATTool.SDL_TRADOS, null);

		result1
			.focusOn(CONTEXT_MATCH)	.set(SEGMENT, 11)	.set(WORD, 16)	.set(CHARACTER, 55)		.set(TOKEN, 16)
			.focusOn(PERCENT_100)	.set(SEGMENT, 263)	.set(WORD, 386)	.set(CHARACTER, 1202)	.set(TOKEN, 259)
			.focusOn(PERCENT_95_99)	.set(SEGMENT, 18)	.set(WORD, 29)	.set(CHARACTER, 171)	.set(TOKEN, 15)
			.focusOn(PERCENT_85_94)	.set(SEGMENT, 9)	.set(WORD, 14)	.set(CHARACTER, 40)		.set(TOKEN, 37)
			.focusOn(PERCENT_75_84)	.set(SEGMENT, 3)	.set(WORD, 17)	.set(CHARACTER, 65)		.set(TOKEN, 42)
			.focusOn(PERCENT_50_74)	.set(SEGMENT, 3)	.set(WORD, 12)	.set(CHARACTER, 79)		.set(TOKEN, 4)
			.focusOn(REPETITIONS)	.set(SEGMENT, 955)	.set(WORD, 1210).set(CHARACTER, 4184)	.set(TOKEN, 1250)
			.focusOn(INTERNAL_95_99).set(SEGMENT, 19)	.set(WORD, 62)	.set(CHARACTER, 338)	.set(TOKEN, 81)
			.focusOn(INTERNAL_85_94).set(SEGMENT, 4)	.set(WORD, 59)	.set(CHARACTER, 310)	.set(TOKEN, 14)
			.focusOn(INTERNAL_75_84).set(SEGMENT, 12)	.set(WORD, 128)	.set(CHARACTER, 464)	.set(TOKEN, 70)
			.focusOn(INTERNAL_50_74).set(SEGMENT, 5)	.set(WORD, 42)	.set(CHARACTER, 233)	.set(TOKEN, 17)
			.focusOn(NEW)			.set(SEGMENT, 349)	.set(WORD, 3861).set(CHARACTER, 20227)	.set(TOKEN, 924)
			.focusOn(TOTAL)			.set(SEGMENT, 1651)	.set(WORD, 5836).set(CHARACTER, 27368)	.set(TOKEN, 2729);
		result1.setCharsPerWord(new BigDecimal("4.69"));

		CATAnalysis result2 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result2
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 119)	.set(WORD, 522)	.set(CHARACTER, 2366)	.set(TOKEN, 97)
			.focusOn(REPETITIONS)			.set(SEGMENT, 281)	.set(WORD, 890)	.set(CHARACTER, 4572)	.set(TOKEN, 212)
			.focusOn(PERCENT_100)			.set(SEGMENT, 121)	.set(WORD, 812)	.set(CHARACTER, 3769)	.set(TOKEN, 58)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 11)	.set(WORD, 90)	.set(CHARACTER, 417)	.set(TOKEN, 7)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 14)	.set(WORD, 127)	.set(CHARACTER, 662)	.set(TOKEN, 23)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 8)	.set(WORD, 40)	.set(CHARACTER, 159)	.set(TOKEN, 6)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 2)	.set(WORD, 26)	.set(CHARACTER, 131)	.set(TOKEN, 2)
			.focusOn(INTERNAL_95_99)		.set(SEGMENT, 38)	.set(WORD, 650)	.set(CHARACTER, 3172)	.set(TOKEN, 55)
			.focusOn(INTERNAL_85_94)		.set(SEGMENT, 18)	.set(WORD, 277)	.set(CHARACTER, 1339)	.set(TOKEN, 26)
			.focusOn(INTERNAL_75_84)		.set(SEGMENT, 24)	.set(WORD, 91)	.set(CHARACTER, 503)	.set(TOKEN, 7)
			.focusOn(INTERNAL_50_74)		.set(SEGMENT, 5)	.set(WORD, 18)	.set(CHARACTER, 95)		.set(TOKEN, 6)
			.focusOn(NEW)					.set(SEGMENT, 240)	.set(WORD, 986)	.set(CHARACTER, 5425)	.set(TOKEN, 65)
			.focusOn(TOTAL)					.set(SEGMENT, 881)	.set(WORD, 4529).set(CHARACTER, 22610)	.set(TOKEN, 564);
		result2.setCharsPerWord(new BigDecimal("4.99"));
		
		CATAnalysis result3 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result3
			.focusOn(REPETITIONS)			.set(SEGMENT, 106)	.set(WORD, 242)	.set(CHARACTER, 910)	.set(TOKEN, 185)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 2)	.set(WORD, 2)	.set(CHARACTER, 14)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 9)	.set(WORD, 9)	.set(CHARACTER, 51)		.set(TOKEN, 9)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 2)	.set(WORD, 2)	.set(CHARACTER, 7)		.set(TOKEN, 2)
			.focusOn(NEW)					.set(SEGMENT, 245)	.set(WORD, 1102).set(CHARACTER, 5614)	.set(TOKEN, 111)
			.focusOn(TOTAL)					.set(SEGMENT, 364)	.set(WORD, 1357).set(CHARACTER, 6596)	.set(TOKEN, 307);
		result3.setCharsPerWord(new BigDecimal("4.86"));
		
		CATAnalysis result4 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result4
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 15)	.set(WORD, 45)	.set(CHARACTER, 450)	.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 3)	.set(WORD, 7)	.set(CHARACTER, 48)		.set(TOKEN, 2)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 8)	.set(WORD, 88)	.set(CHARACTER, 591)	.set(TOKEN, 10)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 1)	.set(WORD, 4)	.set(CHARACTER, 21)		.set(TOKEN, 0)
			.focusOn(NEW)					.set(SEGMENT, 104)	.set(WORD, 1667).set(CHARACTER, 10802)	.set(TOKEN, 161)
			.focusOn(TOTAL)					.set(SEGMENT, 131)	.set(WORD, 1811).set(CHARACTER, 11912)	.set(TOKEN, 173);
		result4.setCharsPerWord(new BigDecimal("6.58"));
		
		CATAnalysis result5 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result5
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 4)	.set(WORD, 7)	.set(CHARACTER, 46)		.set(TOKEN, 0)
			.focusOn(REPETITIONS)			.set(SEGMENT, 29)	.set(WORD, 112)	.set(CHARACTER, 506)	.set(TOKEN, 21)
			.focusOn(PERCENT_100)			.set(SEGMENT, 11)	.set(WORD, 121)	.set(CHARACTER, 725)	.set(TOKEN, 4)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 14)	.set(WORD, 100)	.set(CHARACTER, 661)	.set(TOKEN, 8)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 1)	.set(WORD, 1)	.set(CHARACTER, 12)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 14)	.set(WORD, 76)	.set(CHARACTER, 334)	.set(TOKEN, 19)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 1)	.set(WORD, 6)	.set(CHARACTER, 38)		.set(TOKEN, 0)
			.focusOn(NEW)					.set(SEGMENT, 77)	.set(WORD, 632).set(CHARACTER, 3765)	.set(TOKEN, 155)
			.focusOn(TOTAL)					.set(SEGMENT, 151)	.set(WORD, 1055).set(CHARACTER, 6087)	.set(TOKEN, 207);
		result5.setCharsPerWord(new BigDecimal("5.77"));
		
		CATAnalysis result6 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result6
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 24)	.set(WORD, 26)	.set(CHARACTER, 164)	.set(TOKEN, 10)
			.focusOn(REPETITIONS)			.set(SEGMENT, 1)	.set(WORD, 2)	.set(CHARACTER, 13)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 13)	.set(WORD, 31)	.set(CHARACTER, 263)	.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 23)	.set(WORD, 36)	.set(CHARACTER, 248)	.set(TOKEN, 23)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 1)	.set(WORD, 24)	.set(CHARACTER, 130)	.set(TOKEN, 1)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 6)	.set(WORD, 19)	.set(CHARACTER, 105)	.set(TOKEN, 7)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 2)	.set(WORD, 6)	.set(CHARACTER, 47)		.set(TOKEN, 2)
			.focusOn(NEW)					.set(SEGMENT, 43)	.set(WORD, 222)	.set(CHARACTER, 1562)	.set(TOKEN, 63)
			.focusOn(TOTAL)					.set(SEGMENT, 113)	.set(WORD, 366)	.set(CHARACTER, 2532)	.set(TOKEN, 106);
		result6.setCharsPerWord(new BigDecimal("6.92"));
		
		CATAnalysis result7 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result7
			.focusOn(REPETITIONS)			.set(SEGMENT, 363)	.set(WORD, 561)		.set(CHARACTER, 2441)	.set(TOKEN, 323)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 226)	.set(WORD, 170)		.set(CHARACTER, 1040)	.set(TOKEN, 557)
			.focusOn(PERCENT_100)			.set(SEGMENT, 3498)	.set(WORD, 14912)	.set(CHARACTER, 109562)	.set(TOKEN, 2027)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 315)	.set(WORD, 1110)	.set(CHARACTER, 7453)	.set(TOKEN, 663)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 25)	.set(WORD, 132)		.set(CHARACTER, 865)	.set(TOKEN, 47)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 124)	.set(WORD, 561)		.set(CHARACTER, 3883)	.set(TOKEN, 303)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 24)	.set(WORD, 73)		.set(CHARACTER, 586)	.set(TOKEN, 36)
			.focusOn(INTERNAL_95_99)		.set(SEGMENT, 13)	.set(WORD, 30)		.set(CHARACTER, 163)	.set(TOKEN, 14)
			.focusOn(INTERNAL_85_94)		.set(SEGMENT, 19)	.set(WORD, 81)		.set(CHARACTER, 612)	.set(TOKEN, 21)
			.focusOn(INTERNAL_75_84)		.set(SEGMENT, 122)	.set(WORD, 367)		.set(CHARACTER, 2601)	.set(TOKEN, 176)
			.focusOn(INTERNAL_50_74)		.set(SEGMENT, 17)	.set(WORD, 50)		.set(CHARACTER, 300)	.set(TOKEN, 23)
			.focusOn(NEW)					.set(SEGMENT, 336)	.set(WORD, 710)		.set(CHARACTER, 5227)	.set(TOKEN, 522)
			.focusOn(TOTAL)					.set(SEGMENT, 5082)	.set(WORD, 18757)	.set(CHARACTER, 134733)	.set(TOKEN, 4712);
		result7.setCharsPerWord(new BigDecimal("7.18"));
		
		CATAnalysis result8 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result8
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 308)	.set(WORD, 1302)	.set(CHARACTER, 9310)	.set(TOKEN, 143)
			.focusOn(REPETITIONS)			.set(SEGMENT, 11)	.set(WORD, 101)		.set(CHARACTER, 688)	.set(TOKEN, 15)
			.focusOn(PERCENT_100)			.set(SEGMENT, 45)	.set(WORD, 181)		.set(CHARACTER, 1217)	.set(TOKEN, 33)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 27)	.set(WORD, 82)		.set(CHARACTER, 622)	.set(TOKEN, 14)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 1)	.set(WORD, 5)		.set(CHARACTER, 39)		.set(TOKEN, 0)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 5)	.set(WORD, 44)		.set(CHARACTER, 290)	.set(TOKEN, 9)
			.focusOn(NEW)					.set(SEGMENT, 7)	.set(WORD, 99)		.set(CHARACTER, 661)	.set(TOKEN, 9)
			.focusOn(TOTAL)					.set(SEGMENT, 404)	.set(WORD, 1814)	.set(CHARACTER, 12827)	.set(TOKEN, 223);
		result8.setCharsPerWord(new BigDecimal("7.07"));
		
		CATAnalysis result9 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result9
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 698)	.set(WORD, 698)		.set(CHARACTER, 2221)	.set(TOKEN, 698)
			.focusOn(REPETITIONS)			.set(SEGMENT, 39)	.set(WORD, 175)		.set(CHARACTER, 798)	.set(TOKEN, 109)
			.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 610)	.set(WORD, 3392)	.set(CHARACTER, 17553)	.set(TOKEN, 1560)
			.focusOn(PERCENT_100)			.set(SEGMENT, 1634)	.set(WORD, 5597)	.set(CHARACTER, 28977)	.set(TOKEN, 683)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 89)	.set(WORD, 940)		.set(CHARACTER, 4461)	.set(TOKEN, 219)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 97)	.set(WORD, 1067)	.set(CHARACTER, 5201)	.set(TOKEN, 233)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 85)	.set(WORD, 641)		.set(CHARACTER, 3234)	.set(TOKEN, 80)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 30)	.set(WORD, 210)		.set(CHARACTER, 1124)	.set(TOKEN, 91)
			.focusOn(NEW)					.set(SEGMENT, 346)	.set(WORD, 2819)	.set(CHARACTER, 14515)	.set(TOKEN, 476)
			.focusOn(TOTAL)					.set(SEGMENT, 3628)	.set(WORD, 15539)	.set(CHARACTER, 78084)	.set(TOKEN, 4149);
		result9.setCharsPerWord(new BigDecimal("5.03"));
		
		CATAnalysis result10 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result10
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 24)	.set(WORD, 26)	.set(CHARACTER, 164)	.set(TOKEN, 10)
			.focusOn(REPETITIONS)			.set(SEGMENT, 1)	.set(WORD, 2)	.set(CHARACTER, 13)		.set(TOKEN, 0)
			.focusOn(PERCENT_100)			.set(SEGMENT, 13)	.set(WORD, 31)	.set(CHARACTER, 263)	.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 23)	.set(WORD, 36)	.set(CHARACTER, 248)	.set(TOKEN, 23)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 1)	.set(WORD, 24)	.set(CHARACTER, 130)	.set(TOKEN, 1)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 6)	.set(WORD, 19)	.set(CHARACTER, 105)	.set(TOKEN, 7)
			.focusOn(PERCENT_50_74)			.set(SEGMENT, 2)	.set(WORD, 6)	.set(CHARACTER, 47)		.set(TOKEN, 2)
			.focusOn(NEW)					.set(SEGMENT, 43)	.set(WORD, 222)	.set(CHARACTER, 1562)	.set(TOKEN, 63)
			.focusOn(TOTAL)					.set(SEGMENT, 113)	.set(WORD, 366)	.set(CHARACTER, 2532)	.set(TOKEN, 106);
		result10.setCharsPerWord(new BigDecimal("6.92"));
		
		CATAnalysis result11 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result11
			.focusOn(CONTEXT_MATCH)			.set(SEGMENT, 12)	.set(WORD, 15)	.set(CHARACTER, 129)	.set(TOKEN, 3)
			.focusOn(PERCENT_100)			.set(SEGMENT, 13)	.set(WORD, 15)	.set(CHARACTER, 136)	.set(TOKEN, 0)
			.focusOn(PERCENT_95_99)			.set(SEGMENT, 9)	.set(WORD, 20)	.set(CHARACTER, 125)	.set(TOKEN, 11)
			.focusOn(PERCENT_85_94)			.set(SEGMENT, 2)	.set(WORD, 11)	.set(CHARACTER, 69)		.set(TOKEN, 0)
			.focusOn(PERCENT_75_84)			.set(SEGMENT, 4)	.set(WORD, 24)	.set(CHARACTER, 128)	.set(TOKEN, 3)
			.focusOn(NEW)					.set(SEGMENT, 38)	.set(WORD, 289)	.set(CHARACTER, 1886)	.set(TOKEN, 7)
			.focusOn(TOTAL)					.set(SEGMENT, 78)	.set(WORD, 374)	.set(CHARACTER, 2473)	.set(TOKEN, 24);
		result11.setCharsPerWord(new BigDecimal("6.61"));

		CATAnalysis result12 = new CATAnalysis(CATTool.SDL_TRADOS, null);
		result12
				.focusOn(PERFECT_MATCH).set(SEGMENT, 0).set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(CONTEXT_MATCH).set(SEGMENT, 0).set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(REPETITIONS).set(SEGMENT, 0).set(CHARACTER, 0)		.set(TOKEN, 0)
				.focusOn(CROSS_FILE_REPETITIONS).set(SEGMENT, 0).set(CHARACTER, 0)	.set(TOKEN, 0)
				.focusOn(PERCENT_100).set(SEGMENT, 0).set(CHARACTER, 0)				.set(TOKEN, 0)
				.focusOn(NEW).set(SEGMENT, 13).set(CHARACTER, 190)					.set(TOKEN, 29)
				.focusOn(TOTAL).set(SEGMENT, 13).set(CHARACTER, 190)				.set(TOKEN, 29);
		
		return new Object[][] {
				new Object[] {"/sdl/sdltms2011_1.xlsx", result1},
				new Object[] {"/sdl/sdltms2011_2.xlsx", result2},
				new Object[] {"/sdl/sdltms2011_3.xlsx", result3},
				new Object[] {"/sdl/sdltms2011_4.xlsx", result4},
				new Object[] {"/sdl/sdltms2011_5.xlsx", result5},
				new Object[] {"/sdl/sdltms2011_de_analyse_1311321_de-fr.xlsx", result6},
				new Object[] {"/sdl/sdltms2011_de_an2.xlsx", result7},
				new Object[] {"/sdl/sdltms2011_de_analyse-1309146-de-ru.xls", result8},
				new Object[] {"/sdl/sdltms2011_fr.xlsx", result9},
				new Object[] {"/sdl/sdltms2011_analyse_1311321_de-fr.xlsx", result10},
				new Object[] {"/sdl/sdltms2011_analyse 1404229.xls", result11},
				new Object[] {"/sdl/JA.xlsx", result12},
		};
	}
}

