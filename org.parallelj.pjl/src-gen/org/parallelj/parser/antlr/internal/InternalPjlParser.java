package org.parallelj.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.parallelj.services.PjlGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPjlParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "','", "'metaInformationContainer'", "'Program'", "'{'", "'description'", "'capacity'", "'inputCondition'", "'outputCondition'", "'predicates'", "'}'", "'data'", "'MetaInformationContainer'", "'metaInformation'", "'InputCondition'", "'inputLinks'", "'('", "')'", "'outputLinks'", "'OutputCondition'", "'Predicate'", "'Element'", "'Data'", "'type'", "'-'", "'Link'", "'predicate'", "'destination'", "'Condition'", "'Procedure'", "'executable'", "'join'", "'split'", "'executionMode'", "'ForEachLoop'", "'iterable'", "'WhileLoop'", "'Handler'", "'procedures'", "'Block'", "'BusinessProcedure'", "'MetaInformation'", "'AND'", "'OR'", "'XOR'", "'PARALLEL'", "'ITERATIVE'", "'STREAM'"
    };
    public static final int RULE_ID=5;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int EOF=-1;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__19=19;
    public static final int T__57=57;
    public static final int T__51=51;
    public static final int T__16=16;
    public static final int T__52=52;
    public static final int T__15=15;
    public static final int T__53=53;
    public static final int T__18=18;
    public static final int T__54=54;
    public static final int T__17=17;
    public static final int T__12=12;
    public static final int T__11=11;
    public static final int T__14=14;
    public static final int T__13=13;
    public static final int RULE_INT=6;
    public static final int T__50=50;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int RULE_SL_COMMENT=8;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_STRING=4;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int RULE_WS=9;

    // delegates
    // delegators


        public InternalPjlParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalPjlParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalPjlParser.tokenNames; }
    public String getGrammarFileName() { return "../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g"; }



     	private PjlGrammarAccess grammarAccess;
     	
        public InternalPjlParser(TokenStream input, PjlGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Specification";	
       	}
       	
       	@Override
       	protected PjlGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleSpecification"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:68:1: entryRuleSpecification returns [EObject current=null] : iv_ruleSpecification= ruleSpecification EOF ;
    public final EObject entryRuleSpecification() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSpecification = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:69:2: (iv_ruleSpecification= ruleSpecification EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:70:2: iv_ruleSpecification= ruleSpecification EOF
            {
             newCompositeNode(grammarAccess.getSpecificationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleSpecification_in_entryRuleSpecification75);
            iv_ruleSpecification=ruleSpecification();

            state._fsp--;

             current =iv_ruleSpecification; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleSpecification85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSpecification"


    // $ANTLR start "ruleSpecification"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:77:1: ruleSpecification returns [EObject current=null] : ( () ( ( (lv_programs_1_0= ruleProgram ) ) (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )* )? (otherlv_4= 'metaInformationContainer' ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) ) )? ) ;
    public final EObject ruleSpecification() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_programs_1_0 = null;

        EObject lv_programs_3_0 = null;

        EObject lv_metaInformationContainer_5_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:80:28: ( ( () ( ( (lv_programs_1_0= ruleProgram ) ) (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )* )? (otherlv_4= 'metaInformationContainer' ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) ) )? ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:81:1: ( () ( ( (lv_programs_1_0= ruleProgram ) ) (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )* )? (otherlv_4= 'metaInformationContainer' ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) ) )? )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:81:1: ( () ( ( (lv_programs_1_0= ruleProgram ) ) (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )* )? (otherlv_4= 'metaInformationContainer' ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) ) )? )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:81:2: () ( ( (lv_programs_1_0= ruleProgram ) ) (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )* )? (otherlv_4= 'metaInformationContainer' ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) ) )?
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:81:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:82:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getSpecificationAccess().getSpecificationAction_0(),
                        current);
                

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:87:2: ( ( (lv_programs_1_0= ruleProgram ) ) (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )* )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==13) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:87:3: ( (lv_programs_1_0= ruleProgram ) ) (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )*
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:87:3: ( (lv_programs_1_0= ruleProgram ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:88:1: (lv_programs_1_0= ruleProgram )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:88:1: (lv_programs_1_0= ruleProgram )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:89:3: lv_programs_1_0= ruleProgram
                    {
                     
                    	        newCompositeNode(grammarAccess.getSpecificationAccess().getProgramsProgramParserRuleCall_1_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleProgram_in_ruleSpecification141);
                    lv_programs_1_0=ruleProgram();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSpecificationRule());
                    	        }
                           		add(
                           			current, 
                           			"programs",
                            		lv_programs_1_0, 
                            		"Program");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:105:2: (otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) ) )*
                    loop1:
                    do {
                        int alt1=2;
                        int LA1_0 = input.LA(1);

                        if ( (LA1_0==11) ) {
                            alt1=1;
                        }


                        switch (alt1) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:105:4: otherlv_2= ',' ( (lv_programs_3_0= ruleProgram ) )
                    	    {
                    	    otherlv_2=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleSpecification154); 

                    	        	newLeafNode(otherlv_2, grammarAccess.getSpecificationAccess().getCommaKeyword_1_1_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:109:1: ( (lv_programs_3_0= ruleProgram ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:110:1: (lv_programs_3_0= ruleProgram )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:110:1: (lv_programs_3_0= ruleProgram )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:111:3: lv_programs_3_0= ruleProgram
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSpecificationAccess().getProgramsProgramParserRuleCall_1_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleProgram_in_ruleSpecification175);
                    	    lv_programs_3_0=ruleProgram();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getSpecificationRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"programs",
                    	            		lv_programs_3_0, 
                    	            		"Program");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop1;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:127:6: (otherlv_4= 'metaInformationContainer' ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) ) )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==12) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:127:8: otherlv_4= 'metaInformationContainer' ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) )
                    {
                    otherlv_4=(Token)match(input,12,FollowSets000.FOLLOW_12_in_ruleSpecification192); 

                        	newLeafNode(otherlv_4, grammarAccess.getSpecificationAccess().getMetaInformationContainerKeyword_2_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:131:1: ( (lv_metaInformationContainer_5_0= ruleMetaInformationContainer ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:132:1: (lv_metaInformationContainer_5_0= ruleMetaInformationContainer )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:132:1: (lv_metaInformationContainer_5_0= ruleMetaInformationContainer )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:133:3: lv_metaInformationContainer_5_0= ruleMetaInformationContainer
                    {
                     
                    	        newCompositeNode(grammarAccess.getSpecificationAccess().getMetaInformationContainerMetaInformationContainerParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleMetaInformationContainer_in_ruleSpecification213);
                    lv_metaInformationContainer_5_0=ruleMetaInformationContainer();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSpecificationRule());
                    	        }
                           		set(
                           			current, 
                           			"metaInformationContainer",
                            		lv_metaInformationContainer_5_0, 
                            		"MetaInformationContainer");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSpecification"


    // $ANTLR start "entryRuleElement"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:157:1: entryRuleElement returns [EObject current=null] : iv_ruleElement= ruleElement EOF ;
    public final EObject entryRuleElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:158:2: (iv_ruleElement= ruleElement EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:159:2: iv_ruleElement= ruleElement EOF
            {
             newCompositeNode(grammarAccess.getElementRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleElement_in_entryRuleElement251);
            iv_ruleElement=ruleElement();

            state._fsp--;

             current =iv_ruleElement; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleElement261); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElement"


    // $ANTLR start "ruleElement"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:166:1: ruleElement returns [EObject current=null] : (this_Element_Impl_0= ruleElement_Impl | this_InputCondition_1= ruleInputCondition | this_OutputCondition_2= ruleOutputCondition | this_Condition_Impl_3= ruleCondition_Impl | this_Procedure_Impl_4= ruleProcedure_Impl | this_ForEachLoop_5= ruleForEachLoop | this_WhileLoop_6= ruleWhileLoop | this_Handler_7= ruleHandler | this_Block_8= ruleBlock | this_BusinessProcedure_9= ruleBusinessProcedure ) ;
    public final EObject ruleElement() throws RecognitionException {
        EObject current = null;

        EObject this_Element_Impl_0 = null;

        EObject this_InputCondition_1 = null;

        EObject this_OutputCondition_2 = null;

        EObject this_Condition_Impl_3 = null;

        EObject this_Procedure_Impl_4 = null;

        EObject this_ForEachLoop_5 = null;

        EObject this_WhileLoop_6 = null;

        EObject this_Handler_7 = null;

        EObject this_Block_8 = null;

        EObject this_BusinessProcedure_9 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:169:28: ( (this_Element_Impl_0= ruleElement_Impl | this_InputCondition_1= ruleInputCondition | this_OutputCondition_2= ruleOutputCondition | this_Condition_Impl_3= ruleCondition_Impl | this_Procedure_Impl_4= ruleProcedure_Impl | this_ForEachLoop_5= ruleForEachLoop | this_WhileLoop_6= ruleWhileLoop | this_Handler_7= ruleHandler | this_Block_8= ruleBlock | this_BusinessProcedure_9= ruleBusinessProcedure ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:170:1: (this_Element_Impl_0= ruleElement_Impl | this_InputCondition_1= ruleInputCondition | this_OutputCondition_2= ruleOutputCondition | this_Condition_Impl_3= ruleCondition_Impl | this_Procedure_Impl_4= ruleProcedure_Impl | this_ForEachLoop_5= ruleForEachLoop | this_WhileLoop_6= ruleWhileLoop | this_Handler_7= ruleHandler | this_Block_8= ruleBlock | this_BusinessProcedure_9= ruleBusinessProcedure )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:170:1: (this_Element_Impl_0= ruleElement_Impl | this_InputCondition_1= ruleInputCondition | this_OutputCondition_2= ruleOutputCondition | this_Condition_Impl_3= ruleCondition_Impl | this_Procedure_Impl_4= ruleProcedure_Impl | this_ForEachLoop_5= ruleForEachLoop | this_WhileLoop_6= ruleWhileLoop | this_Handler_7= ruleHandler | this_Block_8= ruleBlock | this_BusinessProcedure_9= ruleBusinessProcedure )
            int alt4=10;
            switch ( input.LA(1) ) {
            case 31:
                {
                alt4=1;
                }
                break;
            case 24:
                {
                alt4=2;
                }
                break;
            case 29:
                {
                alt4=3;
                }
                break;
            case 38:
                {
                alt4=4;
                }
                break;
            case 39:
                {
                alt4=5;
                }
                break;
            case 44:
                {
                alt4=6;
                }
                break;
            case 46:
                {
                alt4=7;
                }
                break;
            case 47:
                {
                alt4=8;
                }
                break;
            case 49:
                {
                alt4=9;
                }
                break;
            case 50:
                {
                alt4=10;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:171:5: this_Element_Impl_0= ruleElement_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getElement_ImplParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleElement_Impl_in_ruleElement308);
                    this_Element_Impl_0=ruleElement_Impl();

                    state._fsp--;

                     
                            current = this_Element_Impl_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:181:5: this_InputCondition_1= ruleInputCondition
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getInputConditionParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleInputCondition_in_ruleElement335);
                    this_InputCondition_1=ruleInputCondition();

                    state._fsp--;

                     
                            current = this_InputCondition_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:191:5: this_OutputCondition_2= ruleOutputCondition
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getOutputConditionParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleOutputCondition_in_ruleElement362);
                    this_OutputCondition_2=ruleOutputCondition();

                    state._fsp--;

                     
                            current = this_OutputCondition_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:201:5: this_Condition_Impl_3= ruleCondition_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getCondition_ImplParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleCondition_Impl_in_ruleElement389);
                    this_Condition_Impl_3=ruleCondition_Impl();

                    state._fsp--;

                     
                            current = this_Condition_Impl_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:211:5: this_Procedure_Impl_4= ruleProcedure_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getProcedure_ImplParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleProcedure_Impl_in_ruleElement416);
                    this_Procedure_Impl_4=ruleProcedure_Impl();

                    state._fsp--;

                     
                            current = this_Procedure_Impl_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:221:5: this_ForEachLoop_5= ruleForEachLoop
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getForEachLoopParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleForEachLoop_in_ruleElement443);
                    this_ForEachLoop_5=ruleForEachLoop();

                    state._fsp--;

                     
                            current = this_ForEachLoop_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 7 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:231:5: this_WhileLoop_6= ruleWhileLoop
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getWhileLoopParserRuleCall_6()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleWhileLoop_in_ruleElement470);
                    this_WhileLoop_6=ruleWhileLoop();

                    state._fsp--;

                     
                            current = this_WhileLoop_6; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 8 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:241:5: this_Handler_7= ruleHandler
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getHandlerParserRuleCall_7()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleHandler_in_ruleElement497);
                    this_Handler_7=ruleHandler();

                    state._fsp--;

                     
                            current = this_Handler_7; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 9 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:251:5: this_Block_8= ruleBlock
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getBlockParserRuleCall_8()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleBlock_in_ruleElement524);
                    this_Block_8=ruleBlock();

                    state._fsp--;

                     
                            current = this_Block_8; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 10 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:261:5: this_BusinessProcedure_9= ruleBusinessProcedure
                    {
                     
                            newCompositeNode(grammarAccess.getElementAccess().getBusinessProcedureParserRuleCall_9()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleBusinessProcedure_in_ruleElement551);
                    this_BusinessProcedure_9=ruleBusinessProcedure();

                    state._fsp--;

                     
                            current = this_BusinessProcedure_9; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleElement"


    // $ANTLR start "entryRuleProcedure"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:277:1: entryRuleProcedure returns [EObject current=null] : iv_ruleProcedure= ruleProcedure EOF ;
    public final EObject entryRuleProcedure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcedure = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:278:2: (iv_ruleProcedure= ruleProcedure EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:279:2: iv_ruleProcedure= ruleProcedure EOF
            {
             newCompositeNode(grammarAccess.getProcedureRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleProcedure_in_entryRuleProcedure586);
            iv_ruleProcedure=ruleProcedure();

            state._fsp--;

             current =iv_ruleProcedure; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleProcedure596); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcedure"


    // $ANTLR start "ruleProcedure"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:286:1: ruleProcedure returns [EObject current=null] : (this_Procedure_Impl_0= ruleProcedure_Impl | this_ForEachLoop_1= ruleForEachLoop | this_WhileLoop_2= ruleWhileLoop | this_Handler_3= ruleHandler | this_Block_4= ruleBlock | this_BusinessProcedure_5= ruleBusinessProcedure ) ;
    public final EObject ruleProcedure() throws RecognitionException {
        EObject current = null;

        EObject this_Procedure_Impl_0 = null;

        EObject this_ForEachLoop_1 = null;

        EObject this_WhileLoop_2 = null;

        EObject this_Handler_3 = null;

        EObject this_Block_4 = null;

        EObject this_BusinessProcedure_5 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:289:28: ( (this_Procedure_Impl_0= ruleProcedure_Impl | this_ForEachLoop_1= ruleForEachLoop | this_WhileLoop_2= ruleWhileLoop | this_Handler_3= ruleHandler | this_Block_4= ruleBlock | this_BusinessProcedure_5= ruleBusinessProcedure ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:290:1: (this_Procedure_Impl_0= ruleProcedure_Impl | this_ForEachLoop_1= ruleForEachLoop | this_WhileLoop_2= ruleWhileLoop | this_Handler_3= ruleHandler | this_Block_4= ruleBlock | this_BusinessProcedure_5= ruleBusinessProcedure )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:290:1: (this_Procedure_Impl_0= ruleProcedure_Impl | this_ForEachLoop_1= ruleForEachLoop | this_WhileLoop_2= ruleWhileLoop | this_Handler_3= ruleHandler | this_Block_4= ruleBlock | this_BusinessProcedure_5= ruleBusinessProcedure )
            int alt5=6;
            switch ( input.LA(1) ) {
            case 39:
                {
                alt5=1;
                }
                break;
            case 44:
                {
                alt5=2;
                }
                break;
            case 46:
                {
                alt5=3;
                }
                break;
            case 47:
                {
                alt5=4;
                }
                break;
            case 49:
                {
                alt5=5;
                }
                break;
            case 50:
                {
                alt5=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:291:5: this_Procedure_Impl_0= ruleProcedure_Impl
                    {
                     
                            newCompositeNode(grammarAccess.getProcedureAccess().getProcedure_ImplParserRuleCall_0()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleProcedure_Impl_in_ruleProcedure643);
                    this_Procedure_Impl_0=ruleProcedure_Impl();

                    state._fsp--;

                     
                            current = this_Procedure_Impl_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:301:5: this_ForEachLoop_1= ruleForEachLoop
                    {
                     
                            newCompositeNode(grammarAccess.getProcedureAccess().getForEachLoopParserRuleCall_1()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleForEachLoop_in_ruleProcedure670);
                    this_ForEachLoop_1=ruleForEachLoop();

                    state._fsp--;

                     
                            current = this_ForEachLoop_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:311:5: this_WhileLoop_2= ruleWhileLoop
                    {
                     
                            newCompositeNode(grammarAccess.getProcedureAccess().getWhileLoopParserRuleCall_2()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleWhileLoop_in_ruleProcedure697);
                    this_WhileLoop_2=ruleWhileLoop();

                    state._fsp--;

                     
                            current = this_WhileLoop_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:321:5: this_Handler_3= ruleHandler
                    {
                     
                            newCompositeNode(grammarAccess.getProcedureAccess().getHandlerParserRuleCall_3()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleHandler_in_ruleProcedure724);
                    this_Handler_3=ruleHandler();

                    state._fsp--;

                     
                            current = this_Handler_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 5 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:331:5: this_Block_4= ruleBlock
                    {
                     
                            newCompositeNode(grammarAccess.getProcedureAccess().getBlockParserRuleCall_4()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleBlock_in_ruleProcedure751);
                    this_Block_4=ruleBlock();

                    state._fsp--;

                     
                            current = this_Block_4; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 6 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:341:5: this_BusinessProcedure_5= ruleBusinessProcedure
                    {
                     
                            newCompositeNode(grammarAccess.getProcedureAccess().getBusinessProcedureParserRuleCall_5()); 
                        
                    pushFollow(FollowSets000.FOLLOW_ruleBusinessProcedure_in_ruleProcedure778);
                    this_BusinessProcedure_5=ruleBusinessProcedure();

                    state._fsp--;

                     
                            current = this_BusinessProcedure_5; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcedure"


    // $ANTLR start "entryRuleProgram"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:357:1: entryRuleProgram returns [EObject current=null] : iv_ruleProgram= ruleProgram EOF ;
    public final EObject entryRuleProgram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgram = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:358:2: (iv_ruleProgram= ruleProgram EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:359:2: iv_ruleProgram= ruleProgram EOF
            {
             newCompositeNode(grammarAccess.getProgramRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleProgram_in_entryRuleProgram813);
            iv_ruleProgram=ruleProgram();

            state._fsp--;

             current =iv_ruleProgram; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleProgram823); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgram"


    // $ANTLR start "ruleProgram"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:366:1: ruleProgram returns [EObject current=null] : (otherlv_0= 'Program' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'capacity' ( (lv_capacity_6_0= ruleEInt ) ) )? otherlv_7= 'inputCondition' ( ( ruleEString ) ) otherlv_9= 'outputCondition' ( ( ruleEString ) ) (otherlv_11= 'predicates' otherlv_12= '{' ( (lv_predicates_13_0= rulePredicate ) ) (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )* otherlv_16= '}' )? ( ( (lv_elements_17_0= ruleElement ) ) (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )* )? (otherlv_20= 'data' otherlv_21= '{' ( (lv_data_22_0= ruleData ) ) (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )* otherlv_25= '}' )? otherlv_26= '}' ) ;
    public final EObject ruleProgram() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_26=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_description_4_0 = null;

        AntlrDatatypeRuleToken lv_capacity_6_0 = null;

        EObject lv_predicates_13_0 = null;

        EObject lv_predicates_15_0 = null;

        EObject lv_elements_17_0 = null;

        EObject lv_elements_19_0 = null;

        EObject lv_data_22_0 = null;

        EObject lv_data_24_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:369:28: ( (otherlv_0= 'Program' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'capacity' ( (lv_capacity_6_0= ruleEInt ) ) )? otherlv_7= 'inputCondition' ( ( ruleEString ) ) otherlv_9= 'outputCondition' ( ( ruleEString ) ) (otherlv_11= 'predicates' otherlv_12= '{' ( (lv_predicates_13_0= rulePredicate ) ) (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )* otherlv_16= '}' )? ( ( (lv_elements_17_0= ruleElement ) ) (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )* )? (otherlv_20= 'data' otherlv_21= '{' ( (lv_data_22_0= ruleData ) ) (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )* otherlv_25= '}' )? otherlv_26= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:370:1: (otherlv_0= 'Program' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'capacity' ( (lv_capacity_6_0= ruleEInt ) ) )? otherlv_7= 'inputCondition' ( ( ruleEString ) ) otherlv_9= 'outputCondition' ( ( ruleEString ) ) (otherlv_11= 'predicates' otherlv_12= '{' ( (lv_predicates_13_0= rulePredicate ) ) (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )* otherlv_16= '}' )? ( ( (lv_elements_17_0= ruleElement ) ) (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )* )? (otherlv_20= 'data' otherlv_21= '{' ( (lv_data_22_0= ruleData ) ) (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )* otherlv_25= '}' )? otherlv_26= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:370:1: (otherlv_0= 'Program' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'capacity' ( (lv_capacity_6_0= ruleEInt ) ) )? otherlv_7= 'inputCondition' ( ( ruleEString ) ) otherlv_9= 'outputCondition' ( ( ruleEString ) ) (otherlv_11= 'predicates' otherlv_12= '{' ( (lv_predicates_13_0= rulePredicate ) ) (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )* otherlv_16= '}' )? ( ( (lv_elements_17_0= ruleElement ) ) (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )* )? (otherlv_20= 'data' otherlv_21= '{' ( (lv_data_22_0= ruleData ) ) (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )* otherlv_25= '}' )? otherlv_26= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:370:3: otherlv_0= 'Program' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'capacity' ( (lv_capacity_6_0= ruleEInt ) ) )? otherlv_7= 'inputCondition' ( ( ruleEString ) ) otherlv_9= 'outputCondition' ( ( ruleEString ) ) (otherlv_11= 'predicates' otherlv_12= '{' ( (lv_predicates_13_0= rulePredicate ) ) (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )* otherlv_16= '}' )? ( ( (lv_elements_17_0= ruleElement ) ) (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )* )? (otherlv_20= 'data' otherlv_21= '{' ( (lv_data_22_0= ruleData ) ) (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )* otherlv_25= '}' )? otherlv_26= '}'
            {
            otherlv_0=(Token)match(input,13,FollowSets000.FOLLOW_13_in_ruleProgram860); 

                	newLeafNode(otherlv_0, grammarAccess.getProgramAccess().getProgramKeyword_0());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:374:1: ( (lv_name_1_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:375:1: (lv_name_1_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:375:1: (lv_name_1_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:376:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getProgramAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProgram881);
            lv_name_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getProgramRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleProgram893); 

                	newLeafNode(otherlv_2, grammarAccess.getProgramAccess().getLeftCurlyBracketKeyword_2());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:396:1: (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:396:3: otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleProgram906); 

                        	newLeafNode(otherlv_3, grammarAccess.getProgramAccess().getDescriptionKeyword_3_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:400:1: ( (lv_description_4_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:401:1: (lv_description_4_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:401:1: (lv_description_4_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:402:3: lv_description_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getProgramAccess().getDescriptionEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProgram927);
                    lv_description_4_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_4_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:418:4: (otherlv_5= 'capacity' ( (lv_capacity_6_0= ruleEInt ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==16) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:418:6: otherlv_5= 'capacity' ( (lv_capacity_6_0= ruleEInt ) )
                    {
                    otherlv_5=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleProgram942); 

                        	newLeafNode(otherlv_5, grammarAccess.getProgramAccess().getCapacityKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:422:1: ( (lv_capacity_6_0= ruleEInt ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:423:1: (lv_capacity_6_0= ruleEInt )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:423:1: (lv_capacity_6_0= ruleEInt )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:424:3: lv_capacity_6_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getProgramAccess().getCapacityEIntParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleProgram963);
                    lv_capacity_6_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	        }
                           		set(
                           			current, 
                           			"capacity",
                            		lv_capacity_6_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,17,FollowSets000.FOLLOW_17_in_ruleProgram977); 

                	newLeafNode(otherlv_7, grammarAccess.getProgramAccess().getInputConditionKeyword_5());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:444:1: ( ( ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:445:1: ( ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:445:1: ( ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:446:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getProgramRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getProgramAccess().getInputConditionInputConditionCrossReference_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProgram1000);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_9=(Token)match(input,18,FollowSets000.FOLLOW_18_in_ruleProgram1012); 

                	newLeafNode(otherlv_9, grammarAccess.getProgramAccess().getOutputConditionKeyword_7());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:463:1: ( ( ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:464:1: ( ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:464:1: ( ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:465:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getProgramRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getProgramAccess().getOutputConditionOutputConditionCrossReference_8_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProgram1035);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:478:2: (otherlv_11= 'predicates' otherlv_12= '{' ( (lv_predicates_13_0= rulePredicate ) ) (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )* otherlv_16= '}' )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==19) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:478:4: otherlv_11= 'predicates' otherlv_12= '{' ( (lv_predicates_13_0= rulePredicate ) ) (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )* otherlv_16= '}'
                    {
                    otherlv_11=(Token)match(input,19,FollowSets000.FOLLOW_19_in_ruleProgram1048); 

                        	newLeafNode(otherlv_11, grammarAccess.getProgramAccess().getPredicatesKeyword_9_0());
                        
                    otherlv_12=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleProgram1060); 

                        	newLeafNode(otherlv_12, grammarAccess.getProgramAccess().getLeftCurlyBracketKeyword_9_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:486:1: ( (lv_predicates_13_0= rulePredicate ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:487:1: (lv_predicates_13_0= rulePredicate )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:487:1: (lv_predicates_13_0= rulePredicate )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:488:3: lv_predicates_13_0= rulePredicate
                    {
                     
                    	        newCompositeNode(grammarAccess.getProgramAccess().getPredicatesPredicateParserRuleCall_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_rulePredicate_in_ruleProgram1081);
                    lv_predicates_13_0=rulePredicate();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	        }
                           		add(
                           			current, 
                           			"predicates",
                            		lv_predicates_13_0, 
                            		"Predicate");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:504:2: (otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) ) )*
                    loop8:
                    do {
                        int alt8=2;
                        int LA8_0 = input.LA(1);

                        if ( (LA8_0==11) ) {
                            alt8=1;
                        }


                        switch (alt8) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:504:4: otherlv_14= ',' ( (lv_predicates_15_0= rulePredicate ) )
                    	    {
                    	    otherlv_14=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleProgram1094); 

                    	        	newLeafNode(otherlv_14, grammarAccess.getProgramAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:508:1: ( (lv_predicates_15_0= rulePredicate ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:509:1: (lv_predicates_15_0= rulePredicate )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:509:1: (lv_predicates_15_0= rulePredicate )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:510:3: lv_predicates_15_0= rulePredicate
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getProgramAccess().getPredicatesPredicateParserRuleCall_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_rulePredicate_in_ruleProgram1115);
                    	    lv_predicates_15_0=rulePredicate();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"predicates",
                    	            		lv_predicates_15_0, 
                    	            		"Predicate");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop8;
                        }
                    } while (true);

                    otherlv_16=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleProgram1129); 

                        	newLeafNode(otherlv_16, grammarAccess.getProgramAccess().getRightCurlyBracketKeyword_9_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:530:3: ( ( (lv_elements_17_0= ruleElement ) ) (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )* )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==24||LA11_0==29||LA11_0==31||(LA11_0>=38 && LA11_0<=39)||LA11_0==44||(LA11_0>=46 && LA11_0<=47)||(LA11_0>=49 && LA11_0<=50)) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:530:4: ( (lv_elements_17_0= ruleElement ) ) (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )*
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:530:4: ( (lv_elements_17_0= ruleElement ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:531:1: (lv_elements_17_0= ruleElement )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:531:1: (lv_elements_17_0= ruleElement )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:532:3: lv_elements_17_0= ruleElement
                    {
                     
                    	        newCompositeNode(grammarAccess.getProgramAccess().getElementsElementParserRuleCall_10_0_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleElement_in_ruleProgram1153);
                    lv_elements_17_0=ruleElement();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	        }
                           		add(
                           			current, 
                           			"elements",
                            		lv_elements_17_0, 
                            		"Element");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:548:2: (otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) ) )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( (LA10_0==11) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:548:4: otherlv_18= ',' ( (lv_elements_19_0= ruleElement ) )
                    	    {
                    	    otherlv_18=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleProgram1166); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getProgramAccess().getCommaKeyword_10_1_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:552:1: ( (lv_elements_19_0= ruleElement ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:553:1: (lv_elements_19_0= ruleElement )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:553:1: (lv_elements_19_0= ruleElement )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:554:3: lv_elements_19_0= ruleElement
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getProgramAccess().getElementsElementParserRuleCall_10_1_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleElement_in_ruleProgram1187);
                    	    lv_elements_19_0=ruleElement();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"elements",
                    	            		lv_elements_19_0, 
                    	            		"Element");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:570:6: (otherlv_20= 'data' otherlv_21= '{' ( (lv_data_22_0= ruleData ) ) (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )* otherlv_25= '}' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==21) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:570:8: otherlv_20= 'data' otherlv_21= '{' ( (lv_data_22_0= ruleData ) ) (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )* otherlv_25= '}'
                    {
                    otherlv_20=(Token)match(input,21,FollowSets000.FOLLOW_21_in_ruleProgram1204); 

                        	newLeafNode(otherlv_20, grammarAccess.getProgramAccess().getDataKeyword_11_0());
                        
                    otherlv_21=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleProgram1216); 

                        	newLeafNode(otherlv_21, grammarAccess.getProgramAccess().getLeftCurlyBracketKeyword_11_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:578:1: ( (lv_data_22_0= ruleData ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:579:1: (lv_data_22_0= ruleData )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:579:1: (lv_data_22_0= ruleData )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:580:3: lv_data_22_0= ruleData
                    {
                     
                    	        newCompositeNode(grammarAccess.getProgramAccess().getDataDataParserRuleCall_11_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleData_in_ruleProgram1237);
                    lv_data_22_0=ruleData();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	        }
                           		add(
                           			current, 
                           			"data",
                            		lv_data_22_0, 
                            		"Data");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:596:2: (otherlv_23= ',' ( (lv_data_24_0= ruleData ) ) )*
                    loop12:
                    do {
                        int alt12=2;
                        int LA12_0 = input.LA(1);

                        if ( (LA12_0==11) ) {
                            alt12=1;
                        }


                        switch (alt12) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:596:4: otherlv_23= ',' ( (lv_data_24_0= ruleData ) )
                    	    {
                    	    otherlv_23=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleProgram1250); 

                    	        	newLeafNode(otherlv_23, grammarAccess.getProgramAccess().getCommaKeyword_11_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:600:1: ( (lv_data_24_0= ruleData ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:601:1: (lv_data_24_0= ruleData )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:601:1: (lv_data_24_0= ruleData )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:602:3: lv_data_24_0= ruleData
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getProgramAccess().getDataDataParserRuleCall_11_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleData_in_ruleProgram1271);
                    	    lv_data_24_0=ruleData();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"data",
                    	            		lv_data_24_0, 
                    	            		"Data");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop12;
                        }
                    } while (true);

                    otherlv_25=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleProgram1285); 

                        	newLeafNode(otherlv_25, grammarAccess.getProgramAccess().getRightCurlyBracketKeyword_11_4());
                        

                    }
                    break;

            }

            otherlv_26=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleProgram1299); 

                	newLeafNode(otherlv_26, grammarAccess.getProgramAccess().getRightCurlyBracketKeyword_12());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgram"


    // $ANTLR start "entryRuleMetaInformationContainer"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:634:1: entryRuleMetaInformationContainer returns [EObject current=null] : iv_ruleMetaInformationContainer= ruleMetaInformationContainer EOF ;
    public final EObject entryRuleMetaInformationContainer() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetaInformationContainer = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:635:2: (iv_ruleMetaInformationContainer= ruleMetaInformationContainer EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:636:2: iv_ruleMetaInformationContainer= ruleMetaInformationContainer EOF
            {
             newCompositeNode(grammarAccess.getMetaInformationContainerRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleMetaInformationContainer_in_entryRuleMetaInformationContainer1335);
            iv_ruleMetaInformationContainer=ruleMetaInformationContainer();

            state._fsp--;

             current =iv_ruleMetaInformationContainer; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleMetaInformationContainer1345); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMetaInformationContainer"


    // $ANTLR start "ruleMetaInformationContainer"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:643:1: ruleMetaInformationContainer returns [EObject current=null] : ( () otherlv_1= 'MetaInformationContainer' otherlv_2= '{' (otherlv_3= 'metaInformation' otherlv_4= '{' ( (lv_metaInformation_5_0= ruleMetaInformation ) ) (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )* otherlv_8= '}' )? otherlv_9= '}' ) ;
    public final EObject ruleMetaInformationContainer() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        EObject lv_metaInformation_5_0 = null;

        EObject lv_metaInformation_7_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:646:28: ( ( () otherlv_1= 'MetaInformationContainer' otherlv_2= '{' (otherlv_3= 'metaInformation' otherlv_4= '{' ( (lv_metaInformation_5_0= ruleMetaInformation ) ) (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )* otherlv_8= '}' )? otherlv_9= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:647:1: ( () otherlv_1= 'MetaInformationContainer' otherlv_2= '{' (otherlv_3= 'metaInformation' otherlv_4= '{' ( (lv_metaInformation_5_0= ruleMetaInformation ) ) (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )* otherlv_8= '}' )? otherlv_9= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:647:1: ( () otherlv_1= 'MetaInformationContainer' otherlv_2= '{' (otherlv_3= 'metaInformation' otherlv_4= '{' ( (lv_metaInformation_5_0= ruleMetaInformation ) ) (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )* otherlv_8= '}' )? otherlv_9= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:647:2: () otherlv_1= 'MetaInformationContainer' otherlv_2= '{' (otherlv_3= 'metaInformation' otherlv_4= '{' ( (lv_metaInformation_5_0= ruleMetaInformation ) ) (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )* otherlv_8= '}' )? otherlv_9= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:647:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:648:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getMetaInformationContainerAccess().getMetaInformationContainerAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,22,FollowSets000.FOLLOW_22_in_ruleMetaInformationContainer1391); 

                	newLeafNode(otherlv_1, grammarAccess.getMetaInformationContainerAccess().getMetaInformationContainerKeyword_1());
                
            otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleMetaInformationContainer1403); 

                	newLeafNode(otherlv_2, grammarAccess.getMetaInformationContainerAccess().getLeftCurlyBracketKeyword_2());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:661:1: (otherlv_3= 'metaInformation' otherlv_4= '{' ( (lv_metaInformation_5_0= ruleMetaInformation ) ) (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )* otherlv_8= '}' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==23) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:661:3: otherlv_3= 'metaInformation' otherlv_4= '{' ( (lv_metaInformation_5_0= ruleMetaInformation ) ) (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )* otherlv_8= '}'
                    {
                    otherlv_3=(Token)match(input,23,FollowSets000.FOLLOW_23_in_ruleMetaInformationContainer1416); 

                        	newLeafNode(otherlv_3, grammarAccess.getMetaInformationContainerAccess().getMetaInformationKeyword_3_0());
                        
                    otherlv_4=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleMetaInformationContainer1428); 

                        	newLeafNode(otherlv_4, grammarAccess.getMetaInformationContainerAccess().getLeftCurlyBracketKeyword_3_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:669:1: ( (lv_metaInformation_5_0= ruleMetaInformation ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:670:1: (lv_metaInformation_5_0= ruleMetaInformation )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:670:1: (lv_metaInformation_5_0= ruleMetaInformation )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:671:3: lv_metaInformation_5_0= ruleMetaInformation
                    {
                     
                    	        newCompositeNode(grammarAccess.getMetaInformationContainerAccess().getMetaInformationMetaInformationParserRuleCall_3_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleMetaInformation_in_ruleMetaInformationContainer1449);
                    lv_metaInformation_5_0=ruleMetaInformation();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getMetaInformationContainerRule());
                    	        }
                           		add(
                           			current, 
                           			"metaInformation",
                            		lv_metaInformation_5_0, 
                            		"MetaInformation");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:687:2: (otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==11) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:687:4: otherlv_6= ',' ( (lv_metaInformation_7_0= ruleMetaInformation ) )
                    	    {
                    	    otherlv_6=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleMetaInformationContainer1462); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getMetaInformationContainerAccess().getCommaKeyword_3_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:691:1: ( (lv_metaInformation_7_0= ruleMetaInformation ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:692:1: (lv_metaInformation_7_0= ruleMetaInformation )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:692:1: (lv_metaInformation_7_0= ruleMetaInformation )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:693:3: lv_metaInformation_7_0= ruleMetaInformation
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getMetaInformationContainerAccess().getMetaInformationMetaInformationParserRuleCall_3_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleMetaInformation_in_ruleMetaInformationContainer1483);
                    	    lv_metaInformation_7_0=ruleMetaInformation();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getMetaInformationContainerRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"metaInformation",
                    	            		lv_metaInformation_7_0, 
                    	            		"MetaInformation");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop14;
                        }
                    } while (true);

                    otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleMetaInformationContainer1497); 

                        	newLeafNode(otherlv_8, grammarAccess.getMetaInformationContainerAccess().getRightCurlyBracketKeyword_3_4());
                        

                    }
                    break;

            }

            otherlv_9=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleMetaInformationContainer1511); 

                	newLeafNode(otherlv_9, grammarAccess.getMetaInformationContainerAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMetaInformationContainer"


    // $ANTLR start "entryRuleEString"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:725:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:726:2: (iv_ruleEString= ruleEString EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:727:2: iv_ruleEString= ruleEString EOF
            {
             newCompositeNode(grammarAccess.getEStringRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString1548);
            iv_ruleEString=ruleEString();

            state._fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString1559); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEString"


    // $ANTLR start "ruleEString"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:734:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:737:28: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:738:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:738:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_STRING) ) {
                alt16=1;
            }
            else if ( (LA16_0==RULE_ID) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:738:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString1599); 

                    		current.merge(this_STRING_0);
                        
                     
                        newLeafNode(this_STRING_0, grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:746:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString1625); 

                    		current.merge(this_ID_1);
                        
                     
                        newLeafNode(this_ID_1, grammarAccess.getEStringAccess().getIDTerminalRuleCall_1()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEString"


    // $ANTLR start "entryRuleInputCondition"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:761:1: entryRuleInputCondition returns [EObject current=null] : iv_ruleInputCondition= ruleInputCondition EOF ;
    public final EObject entryRuleInputCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInputCondition = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:762:2: (iv_ruleInputCondition= ruleInputCondition EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:763:2: iv_ruleInputCondition= ruleInputCondition EOF
            {
             newCompositeNode(grammarAccess.getInputConditionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleInputCondition_in_entryRuleInputCondition1670);
            iv_ruleInputCondition=ruleInputCondition();

            state._fsp--;

             current =iv_ruleInputCondition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleInputCondition1680); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInputCondition"


    // $ANTLR start "ruleInputCondition"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:770:1: ruleInputCondition returns [EObject current=null] : ( () otherlv_1= 'InputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
    public final EObject ruleInputCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        EObject lv_outputLinks_14_0 = null;

        EObject lv_outputLinks_16_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:773:28: ( ( () otherlv_1= 'InputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:774:1: ( () otherlv_1= 'InputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:774:1: ( () otherlv_1= 'InputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:774:2: () otherlv_1= 'InputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:774:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:775:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getInputConditionAccess().getInputConditionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,24,FollowSets000.FOLLOW_24_in_ruleInputCondition1726); 

                	newLeafNode(otherlv_1, grammarAccess.getInputConditionAccess().getInputConditionKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:784:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:785:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:785:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:786:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getInputConditionAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleInputCondition1747);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getInputConditionRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleInputCondition1759); 

                	newLeafNode(otherlv_3, grammarAccess.getInputConditionAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:806:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==15) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:806:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleInputCondition1772); 

                        	newLeafNode(otherlv_4, grammarAccess.getInputConditionAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:810:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:811:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:811:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:812:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getInputConditionAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleInputCondition1793);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInputConditionRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:828:4: (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==25) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:828:6: otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleInputCondition1808); 

                        	newLeafNode(otherlv_6, grammarAccess.getInputConditionAccess().getInputLinksKeyword_5_0());
                        
                    otherlv_7=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleInputCondition1820); 

                        	newLeafNode(otherlv_7, grammarAccess.getInputConditionAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:836:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:837:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:837:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:838:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getInputConditionRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getInputConditionAccess().getInputLinksLinkCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleInputCondition1843);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:851:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0==11) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:851:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleInputCondition1856); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getInputConditionAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:855:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:856:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:856:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:857:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getInputConditionRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getInputConditionAccess().getInputLinksLinkCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleInputCondition1879);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleInputCondition1893); 

                        	newLeafNode(otherlv_11, grammarAccess.getInputConditionAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:874:3: (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==28) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:874:5: otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleInputCondition1908); 

                        	newLeafNode(otherlv_12, grammarAccess.getInputConditionAccess().getOutputLinksKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleInputCondition1920); 

                        	newLeafNode(otherlv_13, grammarAccess.getInputConditionAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:882:1: ( (lv_outputLinks_14_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:883:1: (lv_outputLinks_14_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:883:1: (lv_outputLinks_14_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:884:3: lv_outputLinks_14_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getInputConditionAccess().getOutputLinksLinkParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleInputCondition1941);
                    lv_outputLinks_14_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getInputConditionRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_14_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:900:2: (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )*
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==11) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:900:4: otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) )
                    	    {
                    	    otherlv_15=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleInputCondition1954); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getInputConditionAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:904:1: ( (lv_outputLinks_16_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:905:1: (lv_outputLinks_16_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:905:1: (lv_outputLinks_16_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:906:3: lv_outputLinks_16_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getInputConditionAccess().getOutputLinksLinkParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleInputCondition1975);
                    	    lv_outputLinks_16_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getInputConditionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_16_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop20;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleInputCondition1989); 

                        	newLeafNode(otherlv_17, grammarAccess.getInputConditionAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleInputCondition2003); 

                	newLeafNode(otherlv_18, grammarAccess.getInputConditionAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInputCondition"


    // $ANTLR start "entryRuleOutputCondition"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:938:1: entryRuleOutputCondition returns [EObject current=null] : iv_ruleOutputCondition= ruleOutputCondition EOF ;
    public final EObject entryRuleOutputCondition() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputCondition = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:939:2: (iv_ruleOutputCondition= ruleOutputCondition EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:940:2: iv_ruleOutputCondition= ruleOutputCondition EOF
            {
             newCompositeNode(grammarAccess.getOutputConditionRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleOutputCondition_in_entryRuleOutputCondition2039);
            iv_ruleOutputCondition=ruleOutputCondition();

            state._fsp--;

             current =iv_ruleOutputCondition; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleOutputCondition2049); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOutputCondition"


    // $ANTLR start "ruleOutputCondition"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:947:1: ruleOutputCondition returns [EObject current=null] : ( () otherlv_1= 'OutputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
    public final EObject ruleOutputCondition() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        EObject lv_outputLinks_14_0 = null;

        EObject lv_outputLinks_16_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:950:28: ( ( () otherlv_1= 'OutputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:951:1: ( () otherlv_1= 'OutputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:951:1: ( () otherlv_1= 'OutputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:951:2: () otherlv_1= 'OutputCondition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:951:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:952:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getOutputConditionAccess().getOutputConditionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,29,FollowSets000.FOLLOW_29_in_ruleOutputCondition2095); 

                	newLeafNode(otherlv_1, grammarAccess.getOutputConditionAccess().getOutputConditionKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:961:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:962:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:962:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:963:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getOutputConditionAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleOutputCondition2116);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getOutputConditionRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleOutputCondition2128); 

                	newLeafNode(otherlv_3, grammarAccess.getOutputConditionAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:983:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==15) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:983:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleOutputCondition2141); 

                        	newLeafNode(otherlv_4, grammarAccess.getOutputConditionAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:987:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:988:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:988:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:989:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getOutputConditionAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleOutputCondition2162);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOutputConditionRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1005:4: (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==25) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1005:6: otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleOutputCondition2177); 

                        	newLeafNode(otherlv_6, grammarAccess.getOutputConditionAccess().getInputLinksKeyword_5_0());
                        
                    otherlv_7=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleOutputCondition2189); 

                        	newLeafNode(otherlv_7, grammarAccess.getOutputConditionAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1013:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1014:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1014:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1015:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getOutputConditionRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getOutputConditionAccess().getInputLinksLinkCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleOutputCondition2212);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1028:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==11) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1028:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleOutputCondition2225); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getOutputConditionAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1032:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1033:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1033:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1034:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getOutputConditionRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getOutputConditionAccess().getInputLinksLinkCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleOutputCondition2248);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop23;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleOutputCondition2262); 

                        	newLeafNode(otherlv_11, grammarAccess.getOutputConditionAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1051:3: (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==28) ) {
                alt26=1;
            }
            switch (alt26) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1051:5: otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleOutputCondition2277); 

                        	newLeafNode(otherlv_12, grammarAccess.getOutputConditionAccess().getOutputLinksKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleOutputCondition2289); 

                        	newLeafNode(otherlv_13, grammarAccess.getOutputConditionAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1059:1: ( (lv_outputLinks_14_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1060:1: (lv_outputLinks_14_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1060:1: (lv_outputLinks_14_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1061:3: lv_outputLinks_14_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getOutputConditionAccess().getOutputLinksLinkParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleOutputCondition2310);
                    lv_outputLinks_14_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getOutputConditionRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_14_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1077:2: (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )*
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==11) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1077:4: otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) )
                    	    {
                    	    otherlv_15=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleOutputCondition2323); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getOutputConditionAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1081:1: ( (lv_outputLinks_16_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1082:1: (lv_outputLinks_16_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1082:1: (lv_outputLinks_16_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1083:3: lv_outputLinks_16_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getOutputConditionAccess().getOutputLinksLinkParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleOutputCondition2344);
                    	    lv_outputLinks_16_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getOutputConditionRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_16_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop25;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleOutputCondition2358); 

                        	newLeafNode(otherlv_17, grammarAccess.getOutputConditionAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleOutputCondition2372); 

                	newLeafNode(otherlv_18, grammarAccess.getOutputConditionAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOutputCondition"


    // $ANTLR start "entryRulePredicate"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1115:1: entryRulePredicate returns [EObject current=null] : iv_rulePredicate= rulePredicate EOF ;
    public final EObject entryRulePredicate() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicate = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1116:2: (iv_rulePredicate= rulePredicate EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1117:2: iv_rulePredicate= rulePredicate EOF
            {
             newCompositeNode(grammarAccess.getPredicateRule()); 
            pushFollow(FollowSets000.FOLLOW_rulePredicate_in_entryRulePredicate2408);
            iv_rulePredicate=rulePredicate();

            state._fsp--;

             current =iv_rulePredicate; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRulePredicate2418); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePredicate"


    // $ANTLR start "rulePredicate"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1124:1: rulePredicate returns [EObject current=null] : ( () otherlv_1= 'Predicate' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? otherlv_6= '}' ) ;
    public final EObject rulePredicate() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1127:28: ( ( () otherlv_1= 'Predicate' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? otherlv_6= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1128:1: ( () otherlv_1= 'Predicate' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? otherlv_6= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1128:1: ( () otherlv_1= 'Predicate' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? otherlv_6= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1128:2: () otherlv_1= 'Predicate' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? otherlv_6= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1128:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1129:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getPredicateAccess().getPredicateAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,30,FollowSets000.FOLLOW_30_in_rulePredicate2464); 

                	newLeafNode(otherlv_1, grammarAccess.getPredicateAccess().getPredicateKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1138:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1139:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1139:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1140:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getPredicateAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePredicate2485);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPredicateRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_rulePredicate2497); 

                	newLeafNode(otherlv_3, grammarAccess.getPredicateAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1160:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==15) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1160:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_rulePredicate2510); 

                        	newLeafNode(otherlv_4, grammarAccess.getPredicateAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1164:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1165:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1165:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1166:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getPredicateAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_rulePredicate2531);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPredicateRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_6=(Token)match(input,20,FollowSets000.FOLLOW_20_in_rulePredicate2545); 

                	newLeafNode(otherlv_6, grammarAccess.getPredicateAccess().getRightCurlyBracketKeyword_5());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePredicate"


    // $ANTLR start "entryRuleElement_Impl"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1194:1: entryRuleElement_Impl returns [EObject current=null] : iv_ruleElement_Impl= ruleElement_Impl EOF ;
    public final EObject entryRuleElement_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElement_Impl = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1195:2: (iv_ruleElement_Impl= ruleElement_Impl EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1196:2: iv_ruleElement_Impl= ruleElement_Impl EOF
            {
             newCompositeNode(grammarAccess.getElement_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleElement_Impl_in_entryRuleElement_Impl2581);
            iv_ruleElement_Impl=ruleElement_Impl();

            state._fsp--;

             current =iv_ruleElement_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleElement_Impl2591); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleElement_Impl"


    // $ANTLR start "ruleElement_Impl"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1203:1: ruleElement_Impl returns [EObject current=null] : ( () otherlv_1= 'Element' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
    public final EObject ruleElement_Impl() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        EObject lv_outputLinks_14_0 = null;

        EObject lv_outputLinks_16_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1206:28: ( ( () otherlv_1= 'Element' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1207:1: ( () otherlv_1= 'Element' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1207:1: ( () otherlv_1= 'Element' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1207:2: () otherlv_1= 'Element' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1207:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1208:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getElement_ImplAccess().getElementAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,31,FollowSets000.FOLLOW_31_in_ruleElement_Impl2637); 

                	newLeafNode(otherlv_1, grammarAccess.getElement_ImplAccess().getElementKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1217:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1218:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1218:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1219:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getElement_ImplAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElement_Impl2658);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getElement_ImplRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleElement_Impl2670); 

                	newLeafNode(otherlv_3, grammarAccess.getElement_ImplAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1239:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==15) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1239:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleElement_Impl2683); 

                        	newLeafNode(otherlv_4, grammarAccess.getElement_ImplAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1243:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1244:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1244:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1245:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getElement_ImplAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElement_Impl2704);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getElement_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1261:4: (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==25) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1261:6: otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleElement_Impl2719); 

                        	newLeafNode(otherlv_6, grammarAccess.getElement_ImplAccess().getInputLinksKeyword_5_0());
                        
                    otherlv_7=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleElement_Impl2731); 

                        	newLeafNode(otherlv_7, grammarAccess.getElement_ImplAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1269:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1270:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1270:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1271:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getElement_ImplRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getElement_ImplAccess().getInputLinksLinkCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElement_Impl2754);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1284:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop29:
                    do {
                        int alt29=2;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==11) ) {
                            alt29=1;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1284:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleElement_Impl2767); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getElement_ImplAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1288:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1289:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1289:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1290:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getElement_ImplRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getElement_ImplAccess().getInputLinksLinkCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElement_Impl2790);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleElement_Impl2804); 

                        	newLeafNode(otherlv_11, grammarAccess.getElement_ImplAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1307:3: (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==28) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1307:5: otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleElement_Impl2819); 

                        	newLeafNode(otherlv_12, grammarAccess.getElement_ImplAccess().getOutputLinksKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleElement_Impl2831); 

                        	newLeafNode(otherlv_13, grammarAccess.getElement_ImplAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1315:1: ( (lv_outputLinks_14_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1316:1: (lv_outputLinks_14_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1316:1: (lv_outputLinks_14_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1317:3: lv_outputLinks_14_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getElement_ImplAccess().getOutputLinksLinkParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleElement_Impl2852);
                    lv_outputLinks_14_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getElement_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_14_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1333:2: (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )*
                    loop31:
                    do {
                        int alt31=2;
                        int LA31_0 = input.LA(1);

                        if ( (LA31_0==11) ) {
                            alt31=1;
                        }


                        switch (alt31) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1333:4: otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) )
                    	    {
                    	    otherlv_15=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleElement_Impl2865); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getElement_ImplAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1337:1: ( (lv_outputLinks_16_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1338:1: (lv_outputLinks_16_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1338:1: (lv_outputLinks_16_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1339:3: lv_outputLinks_16_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getElement_ImplAccess().getOutputLinksLinkParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleElement_Impl2886);
                    	    lv_outputLinks_16_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getElement_ImplRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_16_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop31;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleElement_Impl2900); 

                        	newLeafNode(otherlv_17, grammarAccess.getElement_ImplAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleElement_Impl2914); 

                	newLeafNode(otherlv_18, grammarAccess.getElement_ImplAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleElement_Impl"


    // $ANTLR start "entryRuleData"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1371:1: entryRuleData returns [EObject current=null] : iv_ruleData= ruleData EOF ;
    public final EObject entryRuleData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleData = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1372:2: (iv_ruleData= ruleData EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1373:2: iv_ruleData= ruleData EOF
            {
             newCompositeNode(grammarAccess.getDataRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleData_in_entryRuleData2950);
            iv_ruleData=ruleData();

            state._fsp--;

             current =iv_ruleData; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleData2960); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleData"


    // $ANTLR start "ruleData"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1380:1: ruleData returns [EObject current=null] : ( () otherlv_1= 'Data' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'type' ( (lv_type_7_0= ruleEString ) ) )? otherlv_8= '}' ) ;
    public final EObject ruleData() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_type_7_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1383:28: ( ( () otherlv_1= 'Data' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'type' ( (lv_type_7_0= ruleEString ) ) )? otherlv_8= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1384:1: ( () otherlv_1= 'Data' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'type' ( (lv_type_7_0= ruleEString ) ) )? otherlv_8= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1384:1: ( () otherlv_1= 'Data' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'type' ( (lv_type_7_0= ruleEString ) ) )? otherlv_8= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1384:2: () otherlv_1= 'Data' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'type' ( (lv_type_7_0= ruleEString ) ) )? otherlv_8= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1384:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1385:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getDataAccess().getDataAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,32,FollowSets000.FOLLOW_32_in_ruleData3006); 

                	newLeafNode(otherlv_1, grammarAccess.getDataAccess().getDataKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1394:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1395:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1395:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1396:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getDataAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleData3027);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getDataRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleData3039); 

                	newLeafNode(otherlv_3, grammarAccess.getDataAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1416:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==15) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1416:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleData3052); 

                        	newLeafNode(otherlv_4, grammarAccess.getDataAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1420:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1421:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1421:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1422:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getDataAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleData3073);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getDataRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1438:4: (otherlv_6= 'type' ( (lv_type_7_0= ruleEString ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==33) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1438:6: otherlv_6= 'type' ( (lv_type_7_0= ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,33,FollowSets000.FOLLOW_33_in_ruleData3088); 

                        	newLeafNode(otherlv_6, grammarAccess.getDataAccess().getTypeKeyword_5_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1442:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1443:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1443:1: (lv_type_7_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1444:3: lv_type_7_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getDataAccess().getTypeEStringParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleData3109);
                    lv_type_7_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getDataRule());
                    	        }
                           		set(
                           			current, 
                           			"type",
                            		lv_type_7_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_8=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleData3123); 

                	newLeafNode(otherlv_8, grammarAccess.getDataAccess().getRightCurlyBracketKeyword_6());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleData"


    // $ANTLR start "entryRuleEInt"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1472:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1473:2: (iv_ruleEInt= ruleEInt EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1474:2: iv_ruleEInt= ruleEInt EOF
            {
             newCompositeNode(grammarAccess.getEIntRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt3160);
            iv_ruleEInt=ruleEInt();

            state._fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt3171); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEInt"


    // $ANTLR start "ruleEInt"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1481:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1484:28: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1485:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1485:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1485:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1485:2: (kw= '-' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==34) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1486:2: kw= '-'
                    {
                    kw=(Token)match(input,34,FollowSets000.FOLLOW_34_in_ruleEInt3210); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getEIntAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt3227); 

            		current.merge(this_INT_1);
                
             
                newLeafNode(this_INT_1, grammarAccess.getEIntAccess().getINTTerminalRuleCall_1()); 
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEInt"


    // $ANTLR start "entryRuleLink"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1506:1: entryRuleLink returns [EObject current=null] : iv_ruleLink= ruleLink EOF ;
    public final EObject entryRuleLink() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLink = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1507:2: (iv_ruleLink= ruleLink EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1508:2: iv_ruleLink= ruleLink EOF
            {
             newCompositeNode(grammarAccess.getLinkRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleLink_in_entryRuleLink3272);
            iv_ruleLink=ruleLink();

            state._fsp--;

             current =iv_ruleLink; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleLink3282); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLink"


    // $ANTLR start "ruleLink"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1515:1: ruleLink returns [EObject current=null] : (otherlv_0= 'Link' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'predicate' ( ( ruleEString ) ) )? otherlv_7= 'destination' ( ( ruleEString ) ) otherlv_9= '}' ) ;
    public final EObject ruleLink() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_description_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1518:28: ( (otherlv_0= 'Link' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'predicate' ( ( ruleEString ) ) )? otherlv_7= 'destination' ( ( ruleEString ) ) otherlv_9= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1519:1: (otherlv_0= 'Link' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'predicate' ( ( ruleEString ) ) )? otherlv_7= 'destination' ( ( ruleEString ) ) otherlv_9= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1519:1: (otherlv_0= 'Link' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'predicate' ( ( ruleEString ) ) )? otherlv_7= 'destination' ( ( ruleEString ) ) otherlv_9= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1519:3: otherlv_0= 'Link' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'predicate' ( ( ruleEString ) ) )? otherlv_7= 'destination' ( ( ruleEString ) ) otherlv_9= '}'
            {
            otherlv_0=(Token)match(input,35,FollowSets000.FOLLOW_35_in_ruleLink3319); 

                	newLeafNode(otherlv_0, grammarAccess.getLinkAccess().getLinkKeyword_0());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1523:1: ( (lv_name_1_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1524:1: (lv_name_1_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1524:1: (lv_name_1_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1525:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getLinkAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLink3340);
            lv_name_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getLinkRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleLink3352); 

                	newLeafNode(otherlv_2, grammarAccess.getLinkAccess().getLeftCurlyBracketKeyword_2());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1545:1: (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==15) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1545:3: otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleLink3365); 

                        	newLeafNode(otherlv_3, grammarAccess.getLinkAccess().getDescriptionKeyword_3_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1549:1: ( (lv_description_4_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1550:1: (lv_description_4_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1550:1: (lv_description_4_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1551:3: lv_description_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getLinkAccess().getDescriptionEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLink3386);
                    lv_description_4_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getLinkRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_4_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1567:4: (otherlv_5= 'predicate' ( ( ruleEString ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==36) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1567:6: otherlv_5= 'predicate' ( ( ruleEString ) )
                    {
                    otherlv_5=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleLink3401); 

                        	newLeafNode(otherlv_5, grammarAccess.getLinkAccess().getPredicateKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1571:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1572:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1572:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1573:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getLinkRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getLinkAccess().getPredicatePredicateCrossReference_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLink3424);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,37,FollowSets000.FOLLOW_37_in_ruleLink3438); 

                	newLeafNode(otherlv_7, grammarAccess.getLinkAccess().getDestinationKeyword_5());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1590:1: ( ( ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1591:1: ( ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1591:1: ( ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1592:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getLinkRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getLinkAccess().getDestinationElementCrossReference_6_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleLink3461);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_9=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleLink3473); 

                	newLeafNode(otherlv_9, grammarAccess.getLinkAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLink"


    // $ANTLR start "entryRuleCondition_Impl"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1617:1: entryRuleCondition_Impl returns [EObject current=null] : iv_ruleCondition_Impl= ruleCondition_Impl EOF ;
    public final EObject entryRuleCondition_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCondition_Impl = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1618:2: (iv_ruleCondition_Impl= ruleCondition_Impl EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1619:2: iv_ruleCondition_Impl= ruleCondition_Impl EOF
            {
             newCompositeNode(grammarAccess.getCondition_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleCondition_Impl_in_entryRuleCondition_Impl3509);
            iv_ruleCondition_Impl=ruleCondition_Impl();

            state._fsp--;

             current =iv_ruleCondition_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleCondition_Impl3519); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCondition_Impl"


    // $ANTLR start "ruleCondition_Impl"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1626:1: ruleCondition_Impl returns [EObject current=null] : ( () otherlv_1= 'Condition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) ;
    public final EObject ruleCondition_Impl() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        EObject lv_outputLinks_14_0 = null;

        EObject lv_outputLinks_16_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1629:28: ( ( () otherlv_1= 'Condition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1630:1: ( () otherlv_1= 'Condition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1630:1: ( () otherlv_1= 'Condition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1630:2: () otherlv_1= 'Condition' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )? (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )? otherlv_18= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1630:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1631:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getCondition_ImplAccess().getConditionAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,38,FollowSets000.FOLLOW_38_in_ruleCondition_Impl3565); 

                	newLeafNode(otherlv_1, grammarAccess.getCondition_ImplAccess().getConditionKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1640:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1641:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1641:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1642:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getCondition_ImplAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCondition_Impl3586);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getCondition_ImplRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleCondition_Impl3598); 

                	newLeafNode(otherlv_3, grammarAccess.getCondition_ImplAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1662:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==15) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1662:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleCondition_Impl3611); 

                        	newLeafNode(otherlv_4, grammarAccess.getCondition_ImplAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1666:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1667:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1667:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1668:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getCondition_ImplAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCondition_Impl3632);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getCondition_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1684:4: (otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')' )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==25) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1684:6: otherlv_6= 'inputLinks' otherlv_7= '(' ( ( ruleEString ) ) (otherlv_9= ',' ( ( ruleEString ) ) )* otherlv_11= ')'
                    {
                    otherlv_6=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleCondition_Impl3647); 

                        	newLeafNode(otherlv_6, grammarAccess.getCondition_ImplAccess().getInputLinksKeyword_5_0());
                        
                    otherlv_7=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleCondition_Impl3659); 

                        	newLeafNode(otherlv_7, grammarAccess.getCondition_ImplAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1692:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1693:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1693:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1694:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getCondition_ImplRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getCondition_ImplAccess().getInputLinksLinkCrossReference_5_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCondition_Impl3682);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1707:2: (otherlv_9= ',' ( ( ruleEString ) ) )*
                    loop39:
                    do {
                        int alt39=2;
                        int LA39_0 = input.LA(1);

                        if ( (LA39_0==11) ) {
                            alt39=1;
                        }


                        switch (alt39) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1707:4: otherlv_9= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_9=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleCondition_Impl3695); 

                    	        	newLeafNode(otherlv_9, grammarAccess.getCondition_ImplAccess().getCommaKeyword_5_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1711:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1712:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1712:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1713:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getCondition_ImplRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getCondition_ImplAccess().getInputLinksLinkCrossReference_5_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleCondition_Impl3718);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop39;
                        }
                    } while (true);

                    otherlv_11=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleCondition_Impl3732); 

                        	newLeafNode(otherlv_11, grammarAccess.getCondition_ImplAccess().getRightParenthesisKeyword_5_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1730:3: (otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}' )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==28) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1730:5: otherlv_12= 'outputLinks' otherlv_13= '{' ( (lv_outputLinks_14_0= ruleLink ) ) (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )* otherlv_17= '}'
                    {
                    otherlv_12=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleCondition_Impl3747); 

                        	newLeafNode(otherlv_12, grammarAccess.getCondition_ImplAccess().getOutputLinksKeyword_6_0());
                        
                    otherlv_13=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleCondition_Impl3759); 

                        	newLeafNode(otherlv_13, grammarAccess.getCondition_ImplAccess().getLeftCurlyBracketKeyword_6_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1738:1: ( (lv_outputLinks_14_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1739:1: (lv_outputLinks_14_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1739:1: (lv_outputLinks_14_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1740:3: lv_outputLinks_14_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getCondition_ImplAccess().getOutputLinksLinkParserRuleCall_6_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleCondition_Impl3780);
                    lv_outputLinks_14_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getCondition_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_14_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1756:2: (otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) ) )*
                    loop41:
                    do {
                        int alt41=2;
                        int LA41_0 = input.LA(1);

                        if ( (LA41_0==11) ) {
                            alt41=1;
                        }


                        switch (alt41) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1756:4: otherlv_15= ',' ( (lv_outputLinks_16_0= ruleLink ) )
                    	    {
                    	    otherlv_15=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleCondition_Impl3793); 

                    	        	newLeafNode(otherlv_15, grammarAccess.getCondition_ImplAccess().getCommaKeyword_6_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1760:1: ( (lv_outputLinks_16_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1761:1: (lv_outputLinks_16_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1761:1: (lv_outputLinks_16_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1762:3: lv_outputLinks_16_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getCondition_ImplAccess().getOutputLinksLinkParserRuleCall_6_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleCondition_Impl3814);
                    	    lv_outputLinks_16_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getCondition_ImplRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_16_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop41;
                        }
                    } while (true);

                    otherlv_17=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleCondition_Impl3828); 

                        	newLeafNode(otherlv_17, grammarAccess.getCondition_ImplAccess().getRightCurlyBracketKeyword_6_4());
                        

                    }
                    break;

            }

            otherlv_18=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleCondition_Impl3842); 

                	newLeafNode(otherlv_18, grammarAccess.getCondition_ImplAccess().getRightCurlyBracketKeyword_7());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCondition_Impl"


    // $ANTLR start "entryRuleProcedure_Impl"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1794:1: entryRuleProcedure_Impl returns [EObject current=null] : iv_ruleProcedure_Impl= ruleProcedure_Impl EOF ;
    public final EObject entryRuleProcedure_Impl() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcedure_Impl = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1795:2: (iv_ruleProcedure_Impl= ruleProcedure_Impl EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1796:2: iv_ruleProcedure_Impl= ruleProcedure_Impl EOF
            {
             newCompositeNode(grammarAccess.getProcedure_ImplRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleProcedure_Impl_in_entryRuleProcedure_Impl3878);
            iv_ruleProcedure_Impl=ruleProcedure_Impl();

            state._fsp--;

             current =iv_ruleProcedure_Impl; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleProcedure_Impl3888); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProcedure_Impl"


    // $ANTLR start "ruleProcedure_Impl"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1803:1: ruleProcedure_Impl returns [EObject current=null] : ( () otherlv_1= 'Procedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' ) ;
    public final EObject ruleProcedure_Impl() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_executable_7_0 = null;

        Enumerator lv_join_9_0 = null;

        Enumerator lv_split_11_0 = null;

        AntlrDatatypeRuleToken lv_capacity_13_0 = null;

        Enumerator lv_executionMode_15_0 = null;

        EObject lv_outputLinks_24_0 = null;

        EObject lv_outputLinks_26_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1806:28: ( ( () otherlv_1= 'Procedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1807:1: ( () otherlv_1= 'Procedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1807:1: ( () otherlv_1= 'Procedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1807:2: () otherlv_1= 'Procedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1807:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1808:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getProcedure_ImplAccess().getProcedureAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,39,FollowSets000.FOLLOW_39_in_ruleProcedure_Impl3934); 

                	newLeafNode(otherlv_1, grammarAccess.getProcedure_ImplAccess().getProcedureKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1817:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1818:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1818:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1819:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProcedure_Impl3955);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleProcedure_Impl3967); 

                	newLeafNode(otherlv_3, grammarAccess.getProcedure_ImplAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1839:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==15) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1839:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleProcedure_Impl3980); 

                        	newLeafNode(otherlv_4, grammarAccess.getProcedure_ImplAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1843:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1844:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1844:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1845:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProcedure_Impl4001);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1861:4: (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==40) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1861:6: otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleProcedure_Impl4016); 

                        	newLeafNode(otherlv_6, grammarAccess.getProcedure_ImplAccess().getExecutableKeyword_5_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1865:1: ( (lv_executable_7_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1866:1: (lv_executable_7_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1866:1: (lv_executable_7_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1867:3: lv_executable_7_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getExecutableEStringParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProcedure_Impl4037);
                    lv_executable_7_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"executable",
                            		lv_executable_7_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1883:4: (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==41) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1883:6: otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) )
                    {
                    otherlv_8=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleProcedure_Impl4052); 

                        	newLeafNode(otherlv_8, grammarAccess.getProcedure_ImplAccess().getJoinKeyword_6_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1887:1: ( (lv_join_9_0= ruleJoinType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1888:1: (lv_join_9_0= ruleJoinType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1888:1: (lv_join_9_0= ruleJoinType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1889:3: lv_join_9_0= ruleJoinType
                    {
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getJoinJoinTypeEnumRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleJoinType_in_ruleProcedure_Impl4073);
                    lv_join_9_0=ruleJoinType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"join",
                            		lv_join_9_0, 
                            		"JoinType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1905:4: (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==42) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1905:6: otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) )
                    {
                    otherlv_10=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleProcedure_Impl4088); 

                        	newLeafNode(otherlv_10, grammarAccess.getProcedure_ImplAccess().getSplitKeyword_7_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1909:1: ( (lv_split_11_0= ruleSplitType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1910:1: (lv_split_11_0= ruleSplitType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1910:1: (lv_split_11_0= ruleSplitType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1911:3: lv_split_11_0= ruleSplitType
                    {
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getSplitSplitTypeEnumRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSplitType_in_ruleProcedure_Impl4109);
                    lv_split_11_0=ruleSplitType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"split",
                            		lv_split_11_0, 
                            		"SplitType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1927:4: (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==16) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1927:6: otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) )
                    {
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleProcedure_Impl4124); 

                        	newLeafNode(otherlv_12, grammarAccess.getProcedure_ImplAccess().getCapacityKeyword_8_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1931:1: ( (lv_capacity_13_0= ruleEInt ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1932:1: (lv_capacity_13_0= ruleEInt )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1932:1: (lv_capacity_13_0= ruleEInt )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1933:3: lv_capacity_13_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getCapacityEIntParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleProcedure_Impl4145);
                    lv_capacity_13_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"capacity",
                            		lv_capacity_13_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1949:4: (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==43) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1949:6: otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    {
                    otherlv_14=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleProcedure_Impl4160); 

                        	newLeafNode(otherlv_14, grammarAccess.getProcedure_ImplAccess().getExecutionModeKeyword_9_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1953:1: ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1954:1: (lv_executionMode_15_0= ruleExecutionMode )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1954:1: (lv_executionMode_15_0= ruleExecutionMode )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1955:3: lv_executionMode_15_0= ruleExecutionMode
                    {
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getExecutionModeExecutionModeEnumRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleExecutionMode_in_ruleProcedure_Impl4181);
                    lv_executionMode_15_0=ruleExecutionMode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	        }
                           		set(
                           			current, 
                           			"executionMode",
                            		lv_executionMode_15_0, 
                            		"ExecutionMode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1971:4: (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==25) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1971:6: otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')'
                    {
                    otherlv_16=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleProcedure_Impl4196); 

                        	newLeafNode(otherlv_16, grammarAccess.getProcedure_ImplAccess().getInputLinksKeyword_10_0());
                        
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleProcedure_Impl4208); 

                        	newLeafNode(otherlv_17, grammarAccess.getProcedure_ImplAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1979:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1980:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1980:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1981:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getProcedure_ImplRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getInputLinksLinkCrossReference_10_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProcedure_Impl4231);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1994:2: (otherlv_19= ',' ( ( ruleEString ) ) )*
                    loop49:
                    do {
                        int alt49=2;
                        int LA49_0 = input.LA(1);

                        if ( (LA49_0==11) ) {
                            alt49=1;
                        }


                        switch (alt49) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1994:4: otherlv_19= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_19=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleProcedure_Impl4244); 

                    	        	newLeafNode(otherlv_19, grammarAccess.getProcedure_ImplAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1998:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1999:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:1999:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2000:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getProcedure_ImplRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getInputLinksLinkCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleProcedure_Impl4267);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop49;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleProcedure_Impl4281); 

                        	newLeafNode(otherlv_21, grammarAccess.getProcedure_ImplAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2017:3: (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==28) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2017:5: otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}'
                    {
                    otherlv_22=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleProcedure_Impl4296); 

                        	newLeafNode(otherlv_22, grammarAccess.getProcedure_ImplAccess().getOutputLinksKeyword_11_0());
                        
                    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleProcedure_Impl4308); 

                        	newLeafNode(otherlv_23, grammarAccess.getProcedure_ImplAccess().getLeftCurlyBracketKeyword_11_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2025:1: ( (lv_outputLinks_24_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2026:1: (lv_outputLinks_24_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2026:1: (lv_outputLinks_24_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2027:3: lv_outputLinks_24_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getOutputLinksLinkParserRuleCall_11_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleProcedure_Impl4329);
                    lv_outputLinks_24_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_24_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2043:2: (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )*
                    loop51:
                    do {
                        int alt51=2;
                        int LA51_0 = input.LA(1);

                        if ( (LA51_0==11) ) {
                            alt51=1;
                        }


                        switch (alt51) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2043:4: otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) )
                    	    {
                    	    otherlv_25=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleProcedure_Impl4342); 

                    	        	newLeafNode(otherlv_25, grammarAccess.getProcedure_ImplAccess().getCommaKeyword_11_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2047:1: ( (lv_outputLinks_26_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2048:1: (lv_outputLinks_26_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2048:1: (lv_outputLinks_26_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2049:3: lv_outputLinks_26_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getProcedure_ImplAccess().getOutputLinksLinkParserRuleCall_11_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleProcedure_Impl4363);
                    	    lv_outputLinks_26_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getProcedure_ImplRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_26_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop51;
                        }
                    } while (true);

                    otherlv_27=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleProcedure_Impl4377); 

                        	newLeafNode(otherlv_27, grammarAccess.getProcedure_ImplAccess().getRightCurlyBracketKeyword_11_4());
                        

                    }
                    break;

            }

            otherlv_28=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleProcedure_Impl4391); 

                	newLeafNode(otherlv_28, grammarAccess.getProcedure_ImplAccess().getRightCurlyBracketKeyword_12());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProcedure_Impl"


    // $ANTLR start "entryRuleForEachLoop"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2081:1: entryRuleForEachLoop returns [EObject current=null] : iv_ruleForEachLoop= ruleForEachLoop EOF ;
    public final EObject entryRuleForEachLoop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForEachLoop = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2082:2: (iv_ruleForEachLoop= ruleForEachLoop EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2083:2: iv_ruleForEachLoop= ruleForEachLoop EOF
            {
             newCompositeNode(grammarAccess.getForEachLoopRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleForEachLoop_in_entryRuleForEachLoop4427);
            iv_ruleForEachLoop=ruleForEachLoop();

            state._fsp--;

             current =iv_ruleForEachLoop; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleForEachLoop4437); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForEachLoop"


    // $ANTLR start "ruleForEachLoop"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2090:1: ruleForEachLoop returns [EObject current=null] : (otherlv_0= 'ForEachLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'iterable' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' ) ;
    public final EObject ruleForEachLoop() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_description_4_0 = null;

        AntlrDatatypeRuleToken lv_executable_6_0 = null;

        Enumerator lv_join_8_0 = null;

        Enumerator lv_split_10_0 = null;

        AntlrDatatypeRuleToken lv_capacity_12_0 = null;

        Enumerator lv_executionMode_14_0 = null;

        EObject lv_outputLinks_25_0 = null;

        EObject lv_outputLinks_27_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2093:28: ( (otherlv_0= 'ForEachLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'iterable' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2094:1: (otherlv_0= 'ForEachLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'iterable' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2094:1: (otherlv_0= 'ForEachLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'iterable' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2094:3: otherlv_0= 'ForEachLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'iterable' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}'
            {
            otherlv_0=(Token)match(input,44,FollowSets000.FOLLOW_44_in_ruleForEachLoop4474); 

                	newLeafNode(otherlv_0, grammarAccess.getForEachLoopAccess().getForEachLoopKeyword_0());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2098:1: ( (lv_name_1_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2099:1: (lv_name_1_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2099:1: (lv_name_1_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2100:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getForEachLoopAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleForEachLoop4495);
            lv_name_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleForEachLoop4507); 

                	newLeafNode(otherlv_2, grammarAccess.getForEachLoopAccess().getLeftCurlyBracketKeyword_2());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2120:1: (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==15) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2120:3: otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleForEachLoop4520); 

                        	newLeafNode(otherlv_3, grammarAccess.getForEachLoopAccess().getDescriptionKeyword_3_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2124:1: ( (lv_description_4_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2125:1: (lv_description_4_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2125:1: (lv_description_4_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2126:3: lv_description_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getDescriptionEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleForEachLoop4541);
                    lv_description_4_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_4_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2142:4: (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==40) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2142:6: otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) )
                    {
                    otherlv_5=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleForEachLoop4556); 

                        	newLeafNode(otherlv_5, grammarAccess.getForEachLoopAccess().getExecutableKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2146:1: ( (lv_executable_6_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2147:1: (lv_executable_6_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2147:1: (lv_executable_6_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2148:3: lv_executable_6_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getExecutableEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleForEachLoop4577);
                    lv_executable_6_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"executable",
                            		lv_executable_6_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2164:4: (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==41) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2164:6: otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) )
                    {
                    otherlv_7=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleForEachLoop4592); 

                        	newLeafNode(otherlv_7, grammarAccess.getForEachLoopAccess().getJoinKeyword_5_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2168:1: ( (lv_join_8_0= ruleJoinType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2169:1: (lv_join_8_0= ruleJoinType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2169:1: (lv_join_8_0= ruleJoinType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2170:3: lv_join_8_0= ruleJoinType
                    {
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getJoinJoinTypeEnumRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleJoinType_in_ruleForEachLoop4613);
                    lv_join_8_0=ruleJoinType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"join",
                            		lv_join_8_0, 
                            		"JoinType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2186:4: (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==42) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2186:6: otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) )
                    {
                    otherlv_9=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleForEachLoop4628); 

                        	newLeafNode(otherlv_9, grammarAccess.getForEachLoopAccess().getSplitKeyword_6_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2190:1: ( (lv_split_10_0= ruleSplitType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2191:1: (lv_split_10_0= ruleSplitType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2191:1: (lv_split_10_0= ruleSplitType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2192:3: lv_split_10_0= ruleSplitType
                    {
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getSplitSplitTypeEnumRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSplitType_in_ruleForEachLoop4649);
                    lv_split_10_0=ruleSplitType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"split",
                            		lv_split_10_0, 
                            		"SplitType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2208:4: (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )?
            int alt57=2;
            int LA57_0 = input.LA(1);

            if ( (LA57_0==16) ) {
                alt57=1;
            }
            switch (alt57) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2208:6: otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) )
                    {
                    otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleForEachLoop4664); 

                        	newLeafNode(otherlv_11, grammarAccess.getForEachLoopAccess().getCapacityKeyword_7_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2212:1: ( (lv_capacity_12_0= ruleEInt ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2213:1: (lv_capacity_12_0= ruleEInt )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2213:1: (lv_capacity_12_0= ruleEInt )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2214:3: lv_capacity_12_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getCapacityEIntParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleForEachLoop4685);
                    lv_capacity_12_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"capacity",
                            		lv_capacity_12_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2230:4: (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==43) ) {
                alt58=1;
            }
            switch (alt58) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2230:6: otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) )
                    {
                    otherlv_13=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleForEachLoop4700); 

                        	newLeafNode(otherlv_13, grammarAccess.getForEachLoopAccess().getExecutionModeKeyword_8_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2234:1: ( (lv_executionMode_14_0= ruleExecutionMode ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2235:1: (lv_executionMode_14_0= ruleExecutionMode )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2235:1: (lv_executionMode_14_0= ruleExecutionMode )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2236:3: lv_executionMode_14_0= ruleExecutionMode
                    {
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getExecutionModeExecutionModeEnumRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleExecutionMode_in_ruleForEachLoop4721);
                    lv_executionMode_14_0=ruleExecutionMode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"executionMode",
                            		lv_executionMode_14_0, 
                            		"ExecutionMode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2252:4: (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==25) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2252:6: otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')'
                    {
                    otherlv_15=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleForEachLoop4736); 

                        	newLeafNode(otherlv_15, grammarAccess.getForEachLoopAccess().getInputLinksKeyword_9_0());
                        
                    otherlv_16=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleForEachLoop4748); 

                        	newLeafNode(otherlv_16, grammarAccess.getForEachLoopAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2260:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2261:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2261:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2262:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getForEachLoopRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getInputLinksLinkCrossReference_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleForEachLoop4771);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2275:2: (otherlv_18= ',' ( ( ruleEString ) ) )*
                    loop59:
                    do {
                        int alt59=2;
                        int LA59_0 = input.LA(1);

                        if ( (LA59_0==11) ) {
                            alt59=1;
                        }


                        switch (alt59) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2275:4: otherlv_18= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_18=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleForEachLoop4784); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getForEachLoopAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2279:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2280:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2280:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2281:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getForEachLoopRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getInputLinksLinkCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleForEachLoop4807);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop59;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleForEachLoop4821); 

                        	newLeafNode(otherlv_20, grammarAccess.getForEachLoopAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            otherlv_21=(Token)match(input,45,FollowSets000.FOLLOW_45_in_ruleForEachLoop4835); 

                	newLeafNode(otherlv_21, grammarAccess.getForEachLoopAccess().getIterableKeyword_10());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2302:1: ( ( ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2303:1: ( ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2303:1: ( ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2304:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getForEachLoopRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getForEachLoopAccess().getIterableDataCrossReference_11_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleForEachLoop4858);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2317:2: (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==28) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2317:4: otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}'
                    {
                    otherlv_23=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleForEachLoop4871); 

                        	newLeafNode(otherlv_23, grammarAccess.getForEachLoopAccess().getOutputLinksKeyword_12_0());
                        
                    otherlv_24=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleForEachLoop4883); 

                        	newLeafNode(otherlv_24, grammarAccess.getForEachLoopAccess().getLeftCurlyBracketKeyword_12_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2325:1: ( (lv_outputLinks_25_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2326:1: (lv_outputLinks_25_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2326:1: (lv_outputLinks_25_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2327:3: lv_outputLinks_25_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getOutputLinksLinkParserRuleCall_12_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleForEachLoop4904);
                    lv_outputLinks_25_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_25_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2343:2: (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )*
                    loop61:
                    do {
                        int alt61=2;
                        int LA61_0 = input.LA(1);

                        if ( (LA61_0==11) ) {
                            alt61=1;
                        }


                        switch (alt61) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2343:4: otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) )
                    	    {
                    	    otherlv_26=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleForEachLoop4917); 

                    	        	newLeafNode(otherlv_26, grammarAccess.getForEachLoopAccess().getCommaKeyword_12_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2347:1: ( (lv_outputLinks_27_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2348:1: (lv_outputLinks_27_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2348:1: (lv_outputLinks_27_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2349:3: lv_outputLinks_27_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getForEachLoopAccess().getOutputLinksLinkParserRuleCall_12_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleForEachLoop4938);
                    	    lv_outputLinks_27_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getForEachLoopRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_27_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop61;
                        }
                    } while (true);

                    otherlv_28=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleForEachLoop4952); 

                        	newLeafNode(otherlv_28, grammarAccess.getForEachLoopAccess().getRightCurlyBracketKeyword_12_4());
                        

                    }
                    break;

            }

            otherlv_29=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleForEachLoop4966); 

                	newLeafNode(otherlv_29, grammarAccess.getForEachLoopAccess().getRightCurlyBracketKeyword_13());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForEachLoop"


    // $ANTLR start "entryRuleWhileLoop"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2381:1: entryRuleWhileLoop returns [EObject current=null] : iv_ruleWhileLoop= ruleWhileLoop EOF ;
    public final EObject entryRuleWhileLoop() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhileLoop = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2382:2: (iv_ruleWhileLoop= ruleWhileLoop EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2383:2: iv_ruleWhileLoop= ruleWhileLoop EOF
            {
             newCompositeNode(grammarAccess.getWhileLoopRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleWhileLoop_in_entryRuleWhileLoop5002);
            iv_ruleWhileLoop=ruleWhileLoop();

            state._fsp--;

             current =iv_ruleWhileLoop; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleWhileLoop5012); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhileLoop"


    // $ANTLR start "ruleWhileLoop"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2390:1: ruleWhileLoop returns [EObject current=null] : (otherlv_0= 'WhileLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'predicate' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' ) ;
    public final EObject ruleWhileLoop() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        Token otherlv_20=null;
        Token otherlv_21=null;
        Token otherlv_23=null;
        Token otherlv_24=null;
        Token otherlv_26=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_description_4_0 = null;

        AntlrDatatypeRuleToken lv_executable_6_0 = null;

        Enumerator lv_join_8_0 = null;

        Enumerator lv_split_10_0 = null;

        AntlrDatatypeRuleToken lv_capacity_12_0 = null;

        Enumerator lv_executionMode_14_0 = null;

        EObject lv_outputLinks_25_0 = null;

        EObject lv_outputLinks_27_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2393:28: ( (otherlv_0= 'WhileLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'predicate' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2394:1: (otherlv_0= 'WhileLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'predicate' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2394:1: (otherlv_0= 'WhileLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'predicate' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2394:3: otherlv_0= 'WhileLoop' ( (lv_name_1_0= ruleEString ) ) otherlv_2= '{' (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )? (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )? (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )? (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )? (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )? (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )? (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )? otherlv_21= 'predicate' ( ( ruleEString ) ) (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )? otherlv_29= '}'
            {
            otherlv_0=(Token)match(input,46,FollowSets000.FOLLOW_46_in_ruleWhileLoop5049); 

                	newLeafNode(otherlv_0, grammarAccess.getWhileLoopAccess().getWhileLoopKeyword_0());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2398:1: ( (lv_name_1_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2399:1: (lv_name_1_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2399:1: (lv_name_1_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2400:3: lv_name_1_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getWhileLoopAccess().getNameEStringParserRuleCall_1_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWhileLoop5070);
            lv_name_1_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_2=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleWhileLoop5082); 

                	newLeafNode(otherlv_2, grammarAccess.getWhileLoopAccess().getLeftCurlyBracketKeyword_2());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2420:1: (otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==15) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2420:3: otherlv_3= 'description' ( (lv_description_4_0= ruleEString ) )
                    {
                    otherlv_3=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleWhileLoop5095); 

                        	newLeafNode(otherlv_3, grammarAccess.getWhileLoopAccess().getDescriptionKeyword_3_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2424:1: ( (lv_description_4_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2425:1: (lv_description_4_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2425:1: (lv_description_4_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2426:3: lv_description_4_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getDescriptionEStringParserRuleCall_3_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWhileLoop5116);
                    lv_description_4_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_4_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2442:4: (otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) ) )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==40) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2442:6: otherlv_5= 'executable' ( (lv_executable_6_0= ruleEString ) )
                    {
                    otherlv_5=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleWhileLoop5131); 

                        	newLeafNode(otherlv_5, grammarAccess.getWhileLoopAccess().getExecutableKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2446:1: ( (lv_executable_6_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2447:1: (lv_executable_6_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2447:1: (lv_executable_6_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2448:3: lv_executable_6_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getExecutableEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWhileLoop5152);
                    lv_executable_6_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"executable",
                            		lv_executable_6_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2464:4: (otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) ) )?
            int alt65=2;
            int LA65_0 = input.LA(1);

            if ( (LA65_0==41) ) {
                alt65=1;
            }
            switch (alt65) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2464:6: otherlv_7= 'join' ( (lv_join_8_0= ruleJoinType ) )
                    {
                    otherlv_7=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleWhileLoop5167); 

                        	newLeafNode(otherlv_7, grammarAccess.getWhileLoopAccess().getJoinKeyword_5_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2468:1: ( (lv_join_8_0= ruleJoinType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2469:1: (lv_join_8_0= ruleJoinType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2469:1: (lv_join_8_0= ruleJoinType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2470:3: lv_join_8_0= ruleJoinType
                    {
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getJoinJoinTypeEnumRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleJoinType_in_ruleWhileLoop5188);
                    lv_join_8_0=ruleJoinType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"join",
                            		lv_join_8_0, 
                            		"JoinType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2486:4: (otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==42) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2486:6: otherlv_9= 'split' ( (lv_split_10_0= ruleSplitType ) )
                    {
                    otherlv_9=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleWhileLoop5203); 

                        	newLeafNode(otherlv_9, grammarAccess.getWhileLoopAccess().getSplitKeyword_6_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2490:1: ( (lv_split_10_0= ruleSplitType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2491:1: (lv_split_10_0= ruleSplitType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2491:1: (lv_split_10_0= ruleSplitType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2492:3: lv_split_10_0= ruleSplitType
                    {
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getSplitSplitTypeEnumRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSplitType_in_ruleWhileLoop5224);
                    lv_split_10_0=ruleSplitType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"split",
                            		lv_split_10_0, 
                            		"SplitType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2508:4: (otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==16) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2508:6: otherlv_11= 'capacity' ( (lv_capacity_12_0= ruleEInt ) )
                    {
                    otherlv_11=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleWhileLoop5239); 

                        	newLeafNode(otherlv_11, grammarAccess.getWhileLoopAccess().getCapacityKeyword_7_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2512:1: ( (lv_capacity_12_0= ruleEInt ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2513:1: (lv_capacity_12_0= ruleEInt )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2513:1: (lv_capacity_12_0= ruleEInt )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2514:3: lv_capacity_12_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getCapacityEIntParserRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleWhileLoop5260);
                    lv_capacity_12_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"capacity",
                            		lv_capacity_12_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2530:4: (otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==43) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2530:6: otherlv_13= 'executionMode' ( (lv_executionMode_14_0= ruleExecutionMode ) )
                    {
                    otherlv_13=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleWhileLoop5275); 

                        	newLeafNode(otherlv_13, grammarAccess.getWhileLoopAccess().getExecutionModeKeyword_8_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2534:1: ( (lv_executionMode_14_0= ruleExecutionMode ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2535:1: (lv_executionMode_14_0= ruleExecutionMode )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2535:1: (lv_executionMode_14_0= ruleExecutionMode )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2536:3: lv_executionMode_14_0= ruleExecutionMode
                    {
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getExecutionModeExecutionModeEnumRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleExecutionMode_in_ruleWhileLoop5296);
                    lv_executionMode_14_0=ruleExecutionMode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	        }
                           		set(
                           			current, 
                           			"executionMode",
                            		lv_executionMode_14_0, 
                            		"ExecutionMode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2552:4: (otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')' )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==25) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2552:6: otherlv_15= 'inputLinks' otherlv_16= '(' ( ( ruleEString ) ) (otherlv_18= ',' ( ( ruleEString ) ) )* otherlv_20= ')'
                    {
                    otherlv_15=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleWhileLoop5311); 

                        	newLeafNode(otherlv_15, grammarAccess.getWhileLoopAccess().getInputLinksKeyword_9_0());
                        
                    otherlv_16=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleWhileLoop5323); 

                        	newLeafNode(otherlv_16, grammarAccess.getWhileLoopAccess().getLeftParenthesisKeyword_9_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2560:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2561:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2561:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2562:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getWhileLoopRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getInputLinksLinkCrossReference_9_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWhileLoop5346);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2575:2: (otherlv_18= ',' ( ( ruleEString ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==11) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2575:4: otherlv_18= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_18=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleWhileLoop5359); 

                    	        	newLeafNode(otherlv_18, grammarAccess.getWhileLoopAccess().getCommaKeyword_9_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2579:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2580:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2580:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2581:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getWhileLoopRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getInputLinksLinkCrossReference_9_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWhileLoop5382);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop69;
                        }
                    } while (true);

                    otherlv_20=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleWhileLoop5396); 

                        	newLeafNode(otherlv_20, grammarAccess.getWhileLoopAccess().getRightParenthesisKeyword_9_4());
                        

                    }
                    break;

            }

            otherlv_21=(Token)match(input,36,FollowSets000.FOLLOW_36_in_ruleWhileLoop5410); 

                	newLeafNode(otherlv_21, grammarAccess.getWhileLoopAccess().getPredicateKeyword_10());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2602:1: ( ( ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2603:1: ( ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2603:1: ( ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2604:3: ruleEString
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getWhileLoopRule());
            	        }
                    
             
            	        newCompositeNode(grammarAccess.getWhileLoopAccess().getPredicatePredicateCrossReference_11_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleWhileLoop5433);
            ruleEString();

            state._fsp--;

             
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2617:2: (otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}' )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==28) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2617:4: otherlv_23= 'outputLinks' otherlv_24= '{' ( (lv_outputLinks_25_0= ruleLink ) ) (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )* otherlv_28= '}'
                    {
                    otherlv_23=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleWhileLoop5446); 

                        	newLeafNode(otherlv_23, grammarAccess.getWhileLoopAccess().getOutputLinksKeyword_12_0());
                        
                    otherlv_24=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleWhileLoop5458); 

                        	newLeafNode(otherlv_24, grammarAccess.getWhileLoopAccess().getLeftCurlyBracketKeyword_12_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2625:1: ( (lv_outputLinks_25_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2626:1: (lv_outputLinks_25_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2626:1: (lv_outputLinks_25_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2627:3: lv_outputLinks_25_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getOutputLinksLinkParserRuleCall_12_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleWhileLoop5479);
                    lv_outputLinks_25_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_25_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2643:2: (otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) ) )*
                    loop71:
                    do {
                        int alt71=2;
                        int LA71_0 = input.LA(1);

                        if ( (LA71_0==11) ) {
                            alt71=1;
                        }


                        switch (alt71) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2643:4: otherlv_26= ',' ( (lv_outputLinks_27_0= ruleLink ) )
                    	    {
                    	    otherlv_26=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleWhileLoop5492); 

                    	        	newLeafNode(otherlv_26, grammarAccess.getWhileLoopAccess().getCommaKeyword_12_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2647:1: ( (lv_outputLinks_27_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2648:1: (lv_outputLinks_27_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2648:1: (lv_outputLinks_27_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2649:3: lv_outputLinks_27_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getWhileLoopAccess().getOutputLinksLinkParserRuleCall_12_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleWhileLoop5513);
                    	    lv_outputLinks_27_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getWhileLoopRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_27_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop71;
                        }
                    } while (true);

                    otherlv_28=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleWhileLoop5527); 

                        	newLeafNode(otherlv_28, grammarAccess.getWhileLoopAccess().getRightCurlyBracketKeyword_12_4());
                        

                    }
                    break;

            }

            otherlv_29=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleWhileLoop5541); 

                	newLeafNode(otherlv_29, grammarAccess.getWhileLoopAccess().getRightCurlyBracketKeyword_13());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWhileLoop"


    // $ANTLR start "entryRuleHandler"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2681:1: entryRuleHandler returns [EObject current=null] : iv_ruleHandler= ruleHandler EOF ;
    public final EObject entryRuleHandler() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleHandler = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2682:2: (iv_ruleHandler= ruleHandler EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2683:2: iv_ruleHandler= ruleHandler EOF
            {
             newCompositeNode(grammarAccess.getHandlerRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleHandler_in_entryRuleHandler5577);
            iv_ruleHandler=ruleHandler();

            state._fsp--;

             current =iv_ruleHandler; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleHandler5587); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleHandler"


    // $ANTLR start "ruleHandler"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2690:1: ruleHandler returns [EObject current=null] : ( () otherlv_1= 'Handler' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'procedures' otherlv_23= '(' ( ( ruleEString ) ) (otherlv_25= ',' ( ( ruleEString ) ) )* otherlv_27= ')' )? (otherlv_28= 'outputLinks' otherlv_29= '{' ( (lv_outputLinks_30_0= ruleLink ) ) (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )* otherlv_33= '}' )? otherlv_34= '}' ) ;
    public final EObject ruleHandler() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        Token otherlv_31=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_executable_7_0 = null;

        Enumerator lv_join_9_0 = null;

        Enumerator lv_split_11_0 = null;

        AntlrDatatypeRuleToken lv_capacity_13_0 = null;

        Enumerator lv_executionMode_15_0 = null;

        EObject lv_outputLinks_30_0 = null;

        EObject lv_outputLinks_32_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2693:28: ( ( () otherlv_1= 'Handler' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'procedures' otherlv_23= '(' ( ( ruleEString ) ) (otherlv_25= ',' ( ( ruleEString ) ) )* otherlv_27= ')' )? (otherlv_28= 'outputLinks' otherlv_29= '{' ( (lv_outputLinks_30_0= ruleLink ) ) (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )* otherlv_33= '}' )? otherlv_34= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2694:1: ( () otherlv_1= 'Handler' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'procedures' otherlv_23= '(' ( ( ruleEString ) ) (otherlv_25= ',' ( ( ruleEString ) ) )* otherlv_27= ')' )? (otherlv_28= 'outputLinks' otherlv_29= '{' ( (lv_outputLinks_30_0= ruleLink ) ) (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )* otherlv_33= '}' )? otherlv_34= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2694:1: ( () otherlv_1= 'Handler' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'procedures' otherlv_23= '(' ( ( ruleEString ) ) (otherlv_25= ',' ( ( ruleEString ) ) )* otherlv_27= ')' )? (otherlv_28= 'outputLinks' otherlv_29= '{' ( (lv_outputLinks_30_0= ruleLink ) ) (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )* otherlv_33= '}' )? otherlv_34= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2694:2: () otherlv_1= 'Handler' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'procedures' otherlv_23= '(' ( ( ruleEString ) ) (otherlv_25= ',' ( ( ruleEString ) ) )* otherlv_27= ')' )? (otherlv_28= 'outputLinks' otherlv_29= '{' ( (lv_outputLinks_30_0= ruleLink ) ) (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )* otherlv_33= '}' )? otherlv_34= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2694:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2695:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getHandlerAccess().getHandlerAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,47,FollowSets000.FOLLOW_47_in_ruleHandler5633); 

                	newLeafNode(otherlv_1, grammarAccess.getHandlerAccess().getHandlerKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2704:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2705:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2705:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2706:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getHandlerAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleHandler5654);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getHandlerRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleHandler5666); 

                	newLeafNode(otherlv_3, grammarAccess.getHandlerAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2726:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==15) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2726:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleHandler5679); 

                        	newLeafNode(otherlv_4, grammarAccess.getHandlerAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2730:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2731:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2731:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2732:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleHandler5700);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2748:4: (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==40) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2748:6: otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleHandler5715); 

                        	newLeafNode(otherlv_6, grammarAccess.getHandlerAccess().getExecutableKeyword_5_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2752:1: ( (lv_executable_7_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2753:1: (lv_executable_7_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2753:1: (lv_executable_7_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2754:3: lv_executable_7_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getExecutableEStringParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleHandler5736);
                    lv_executable_7_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	        }
                           		set(
                           			current, 
                           			"executable",
                            		lv_executable_7_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2770:4: (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==41) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2770:6: otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) )
                    {
                    otherlv_8=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleHandler5751); 

                        	newLeafNode(otherlv_8, grammarAccess.getHandlerAccess().getJoinKeyword_6_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2774:1: ( (lv_join_9_0= ruleJoinType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2775:1: (lv_join_9_0= ruleJoinType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2775:1: (lv_join_9_0= ruleJoinType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2776:3: lv_join_9_0= ruleJoinType
                    {
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getJoinJoinTypeEnumRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleJoinType_in_ruleHandler5772);
                    lv_join_9_0=ruleJoinType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	        }
                           		set(
                           			current, 
                           			"join",
                            		lv_join_9_0, 
                            		"JoinType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2792:4: (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==42) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2792:6: otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) )
                    {
                    otherlv_10=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleHandler5787); 

                        	newLeafNode(otherlv_10, grammarAccess.getHandlerAccess().getSplitKeyword_7_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2796:1: ( (lv_split_11_0= ruleSplitType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2797:1: (lv_split_11_0= ruleSplitType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2797:1: (lv_split_11_0= ruleSplitType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2798:3: lv_split_11_0= ruleSplitType
                    {
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getSplitSplitTypeEnumRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSplitType_in_ruleHandler5808);
                    lv_split_11_0=ruleSplitType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	        }
                           		set(
                           			current, 
                           			"split",
                            		lv_split_11_0, 
                            		"SplitType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2814:4: (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==16) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2814:6: otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) )
                    {
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleHandler5823); 

                        	newLeafNode(otherlv_12, grammarAccess.getHandlerAccess().getCapacityKeyword_8_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2818:1: ( (lv_capacity_13_0= ruleEInt ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2819:1: (lv_capacity_13_0= ruleEInt )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2819:1: (lv_capacity_13_0= ruleEInt )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2820:3: lv_capacity_13_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getCapacityEIntParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleHandler5844);
                    lv_capacity_13_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	        }
                           		set(
                           			current, 
                           			"capacity",
                            		lv_capacity_13_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2836:4: (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==43) ) {
                alt78=1;
            }
            switch (alt78) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2836:6: otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    {
                    otherlv_14=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleHandler5859); 

                        	newLeafNode(otherlv_14, grammarAccess.getHandlerAccess().getExecutionModeKeyword_9_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2840:1: ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2841:1: (lv_executionMode_15_0= ruleExecutionMode )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2841:1: (lv_executionMode_15_0= ruleExecutionMode )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2842:3: lv_executionMode_15_0= ruleExecutionMode
                    {
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getExecutionModeExecutionModeEnumRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleExecutionMode_in_ruleHandler5880);
                    lv_executionMode_15_0=ruleExecutionMode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	        }
                           		set(
                           			current, 
                           			"executionMode",
                            		lv_executionMode_15_0, 
                            		"ExecutionMode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2858:4: (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==25) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2858:6: otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')'
                    {
                    otherlv_16=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleHandler5895); 

                        	newLeafNode(otherlv_16, grammarAccess.getHandlerAccess().getInputLinksKeyword_10_0());
                        
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleHandler5907); 

                        	newLeafNode(otherlv_17, grammarAccess.getHandlerAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2866:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2867:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2867:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2868:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getHandlerRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getInputLinksLinkCrossReference_10_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleHandler5930);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2881:2: (otherlv_19= ',' ( ( ruleEString ) ) )*
                    loop79:
                    do {
                        int alt79=2;
                        int LA79_0 = input.LA(1);

                        if ( (LA79_0==11) ) {
                            alt79=1;
                        }


                        switch (alt79) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2881:4: otherlv_19= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_19=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleHandler5943); 

                    	        	newLeafNode(otherlv_19, grammarAccess.getHandlerAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2885:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2886:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2886:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2887:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getHandlerRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getHandlerAccess().getInputLinksLinkCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleHandler5966);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop79;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleHandler5980); 

                        	newLeafNode(otherlv_21, grammarAccess.getHandlerAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2904:3: (otherlv_22= 'procedures' otherlv_23= '(' ( ( ruleEString ) ) (otherlv_25= ',' ( ( ruleEString ) ) )* otherlv_27= ')' )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==48) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2904:5: otherlv_22= 'procedures' otherlv_23= '(' ( ( ruleEString ) ) (otherlv_25= ',' ( ( ruleEString ) ) )* otherlv_27= ')'
                    {
                    otherlv_22=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleHandler5995); 

                        	newLeafNode(otherlv_22, grammarAccess.getHandlerAccess().getProceduresKeyword_11_0());
                        
                    otherlv_23=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleHandler6007); 

                        	newLeafNode(otherlv_23, grammarAccess.getHandlerAccess().getLeftParenthesisKeyword_11_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2912:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2913:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2913:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2914:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getHandlerRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getProceduresProcedureCrossReference_11_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleHandler6030);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2927:2: (otherlv_25= ',' ( ( ruleEString ) ) )*
                    loop81:
                    do {
                        int alt81=2;
                        int LA81_0 = input.LA(1);

                        if ( (LA81_0==11) ) {
                            alt81=1;
                        }


                        switch (alt81) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2927:4: otherlv_25= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_25=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleHandler6043); 

                    	        	newLeafNode(otherlv_25, grammarAccess.getHandlerAccess().getCommaKeyword_11_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2931:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2932:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2932:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2933:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getHandlerRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getHandlerAccess().getProceduresProcedureCrossReference_11_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleHandler6066);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop81;
                        }
                    } while (true);

                    otherlv_27=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleHandler6080); 

                        	newLeafNode(otherlv_27, grammarAccess.getHandlerAccess().getRightParenthesisKeyword_11_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2950:3: (otherlv_28= 'outputLinks' otherlv_29= '{' ( (lv_outputLinks_30_0= ruleLink ) ) (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )* otherlv_33= '}' )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==28) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2950:5: otherlv_28= 'outputLinks' otherlv_29= '{' ( (lv_outputLinks_30_0= ruleLink ) ) (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )* otherlv_33= '}'
                    {
                    otherlv_28=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleHandler6095); 

                        	newLeafNode(otherlv_28, grammarAccess.getHandlerAccess().getOutputLinksKeyword_12_0());
                        
                    otherlv_29=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleHandler6107); 

                        	newLeafNode(otherlv_29, grammarAccess.getHandlerAccess().getLeftCurlyBracketKeyword_12_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2958:1: ( (lv_outputLinks_30_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2959:1: (lv_outputLinks_30_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2959:1: (lv_outputLinks_30_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2960:3: lv_outputLinks_30_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getHandlerAccess().getOutputLinksLinkParserRuleCall_12_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleHandler6128);
                    lv_outputLinks_30_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_30_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2976:2: (otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) ) )*
                    loop83:
                    do {
                        int alt83=2;
                        int LA83_0 = input.LA(1);

                        if ( (LA83_0==11) ) {
                            alt83=1;
                        }


                        switch (alt83) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2976:4: otherlv_31= ',' ( (lv_outputLinks_32_0= ruleLink ) )
                    	    {
                    	    otherlv_31=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleHandler6141); 

                    	        	newLeafNode(otherlv_31, grammarAccess.getHandlerAccess().getCommaKeyword_12_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2980:1: ( (lv_outputLinks_32_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2981:1: (lv_outputLinks_32_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2981:1: (lv_outputLinks_32_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:2982:3: lv_outputLinks_32_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getHandlerAccess().getOutputLinksLinkParserRuleCall_12_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleHandler6162);
                    	    lv_outputLinks_32_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getHandlerRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_32_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop83;
                        }
                    } while (true);

                    otherlv_33=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleHandler6176); 

                        	newLeafNode(otherlv_33, grammarAccess.getHandlerAccess().getRightCurlyBracketKeyword_12_4());
                        

                    }
                    break;

            }

            otherlv_34=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleHandler6190); 

                	newLeafNode(otherlv_34, grammarAccess.getHandlerAccess().getRightCurlyBracketKeyword_13());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleHandler"


    // $ANTLR start "entryRuleBlock"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3014:1: entryRuleBlock returns [EObject current=null] : iv_ruleBlock= ruleBlock EOF ;
    public final EObject entryRuleBlock() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlock = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3015:2: (iv_ruleBlock= ruleBlock EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3016:2: iv_ruleBlock= ruleBlock EOF
            {
             newCompositeNode(grammarAccess.getBlockRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleBlock_in_entryRuleBlock6226);
            iv_ruleBlock=ruleBlock();

            state._fsp--;

             current =iv_ruleBlock; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleBlock6236); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBlock"


    // $ANTLR start "ruleBlock"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3023:1: ruleBlock returns [EObject current=null] : ( () otherlv_1= 'Block' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? (otherlv_28= 'procedures' otherlv_29= '{' ( (lv_procedures_30_0= ruleProcedure ) ) (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )* otherlv_33= '}' )? otherlv_34= '}' ) ;
    public final EObject ruleBlock() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        Token otherlv_29=null;
        Token otherlv_31=null;
        Token otherlv_33=null;
        Token otherlv_34=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_executable_7_0 = null;

        Enumerator lv_join_9_0 = null;

        Enumerator lv_split_11_0 = null;

        AntlrDatatypeRuleToken lv_capacity_13_0 = null;

        Enumerator lv_executionMode_15_0 = null;

        EObject lv_outputLinks_24_0 = null;

        EObject lv_outputLinks_26_0 = null;

        EObject lv_procedures_30_0 = null;

        EObject lv_procedures_32_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3026:28: ( ( () otherlv_1= 'Block' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? (otherlv_28= 'procedures' otherlv_29= '{' ( (lv_procedures_30_0= ruleProcedure ) ) (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )* otherlv_33= '}' )? otherlv_34= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3027:1: ( () otherlv_1= 'Block' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? (otherlv_28= 'procedures' otherlv_29= '{' ( (lv_procedures_30_0= ruleProcedure ) ) (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )* otherlv_33= '}' )? otherlv_34= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3027:1: ( () otherlv_1= 'Block' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? (otherlv_28= 'procedures' otherlv_29= '{' ( (lv_procedures_30_0= ruleProcedure ) ) (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )* otherlv_33= '}' )? otherlv_34= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3027:2: () otherlv_1= 'Block' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? (otherlv_28= 'procedures' otherlv_29= '{' ( (lv_procedures_30_0= ruleProcedure ) ) (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )* otherlv_33= '}' )? otherlv_34= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3027:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3028:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getBlockAccess().getBlockAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,49,FollowSets000.FOLLOW_49_in_ruleBlock6282); 

                	newLeafNode(otherlv_1, grammarAccess.getBlockAccess().getBlockKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3037:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3038:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3038:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3039:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getBlockAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBlock6303);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBlockRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleBlock6315); 

                	newLeafNode(otherlv_3, grammarAccess.getBlockAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3059:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==15) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3059:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleBlock6328); 

                        	newLeafNode(otherlv_4, grammarAccess.getBlockAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3063:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3064:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3064:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3065:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBlock6349);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3081:4: (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==40) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3081:6: otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleBlock6364); 

                        	newLeafNode(otherlv_6, grammarAccess.getBlockAccess().getExecutableKeyword_5_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3085:1: ( (lv_executable_7_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3086:1: (lv_executable_7_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3086:1: (lv_executable_7_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3087:3: lv_executable_7_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getExecutableEStringParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBlock6385);
                    lv_executable_7_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		set(
                           			current, 
                           			"executable",
                            		lv_executable_7_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3103:4: (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==41) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3103:6: otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) )
                    {
                    otherlv_8=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleBlock6400); 

                        	newLeafNode(otherlv_8, grammarAccess.getBlockAccess().getJoinKeyword_6_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3107:1: ( (lv_join_9_0= ruleJoinType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3108:1: (lv_join_9_0= ruleJoinType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3108:1: (lv_join_9_0= ruleJoinType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3109:3: lv_join_9_0= ruleJoinType
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getJoinJoinTypeEnumRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleJoinType_in_ruleBlock6421);
                    lv_join_9_0=ruleJoinType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		set(
                           			current, 
                           			"join",
                            		lv_join_9_0, 
                            		"JoinType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3125:4: (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==42) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3125:6: otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) )
                    {
                    otherlv_10=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleBlock6436); 

                        	newLeafNode(otherlv_10, grammarAccess.getBlockAccess().getSplitKeyword_7_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3129:1: ( (lv_split_11_0= ruleSplitType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3130:1: (lv_split_11_0= ruleSplitType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3130:1: (lv_split_11_0= ruleSplitType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3131:3: lv_split_11_0= ruleSplitType
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getSplitSplitTypeEnumRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSplitType_in_ruleBlock6457);
                    lv_split_11_0=ruleSplitType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		set(
                           			current, 
                           			"split",
                            		lv_split_11_0, 
                            		"SplitType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3147:4: (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==16) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3147:6: otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) )
                    {
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleBlock6472); 

                        	newLeafNode(otherlv_12, grammarAccess.getBlockAccess().getCapacityKeyword_8_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3151:1: ( (lv_capacity_13_0= ruleEInt ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3152:1: (lv_capacity_13_0= ruleEInt )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3152:1: (lv_capacity_13_0= ruleEInt )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3153:3: lv_capacity_13_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getCapacityEIntParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleBlock6493);
                    lv_capacity_13_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		set(
                           			current, 
                           			"capacity",
                            		lv_capacity_13_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3169:4: (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==43) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3169:6: otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    {
                    otherlv_14=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleBlock6508); 

                        	newLeafNode(otherlv_14, grammarAccess.getBlockAccess().getExecutionModeKeyword_9_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3173:1: ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3174:1: (lv_executionMode_15_0= ruleExecutionMode )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3174:1: (lv_executionMode_15_0= ruleExecutionMode )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3175:3: lv_executionMode_15_0= ruleExecutionMode
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getExecutionModeExecutionModeEnumRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleExecutionMode_in_ruleBlock6529);
                    lv_executionMode_15_0=ruleExecutionMode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		set(
                           			current, 
                           			"executionMode",
                            		lv_executionMode_15_0, 
                            		"ExecutionMode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3191:4: (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==25) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3191:6: otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')'
                    {
                    otherlv_16=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleBlock6544); 

                        	newLeafNode(otherlv_16, grammarAccess.getBlockAccess().getInputLinksKeyword_10_0());
                        
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleBlock6556); 

                        	newLeafNode(otherlv_17, grammarAccess.getBlockAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3199:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3200:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3200:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3201:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getBlockRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getInputLinksLinkCrossReference_10_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBlock6579);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3214:2: (otherlv_19= ',' ( ( ruleEString ) ) )*
                    loop91:
                    do {
                        int alt91=2;
                        int LA91_0 = input.LA(1);

                        if ( (LA91_0==11) ) {
                            alt91=1;
                        }


                        switch (alt91) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3214:4: otherlv_19= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_19=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleBlock6592); 

                    	        	newLeafNode(otherlv_19, grammarAccess.getBlockAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3218:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3219:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3219:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3220:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getBlockRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getBlockAccess().getInputLinksLinkCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBlock6615);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop91;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleBlock6629); 

                        	newLeafNode(otherlv_21, grammarAccess.getBlockAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3237:3: (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==28) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3237:5: otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}'
                    {
                    otherlv_22=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleBlock6644); 

                        	newLeafNode(otherlv_22, grammarAccess.getBlockAccess().getOutputLinksKeyword_11_0());
                        
                    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleBlock6656); 

                        	newLeafNode(otherlv_23, grammarAccess.getBlockAccess().getLeftCurlyBracketKeyword_11_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3245:1: ( (lv_outputLinks_24_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3246:1: (lv_outputLinks_24_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3246:1: (lv_outputLinks_24_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3247:3: lv_outputLinks_24_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getOutputLinksLinkParserRuleCall_11_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleBlock6677);
                    lv_outputLinks_24_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_24_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3263:2: (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )*
                    loop93:
                    do {
                        int alt93=2;
                        int LA93_0 = input.LA(1);

                        if ( (LA93_0==11) ) {
                            alt93=1;
                        }


                        switch (alt93) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3263:4: otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) )
                    	    {
                    	    otherlv_25=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleBlock6690); 

                    	        	newLeafNode(otherlv_25, grammarAccess.getBlockAccess().getCommaKeyword_11_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3267:1: ( (lv_outputLinks_26_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3268:1: (lv_outputLinks_26_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3268:1: (lv_outputLinks_26_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3269:3: lv_outputLinks_26_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getBlockAccess().getOutputLinksLinkParserRuleCall_11_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleBlock6711);
                    	    lv_outputLinks_26_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_26_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop93;
                        }
                    } while (true);

                    otherlv_27=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleBlock6725); 

                        	newLeafNode(otherlv_27, grammarAccess.getBlockAccess().getRightCurlyBracketKeyword_11_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3289:3: (otherlv_28= 'procedures' otherlv_29= '{' ( (lv_procedures_30_0= ruleProcedure ) ) (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )* otherlv_33= '}' )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==48) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3289:5: otherlv_28= 'procedures' otherlv_29= '{' ( (lv_procedures_30_0= ruleProcedure ) ) (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )* otherlv_33= '}'
                    {
                    otherlv_28=(Token)match(input,48,FollowSets000.FOLLOW_48_in_ruleBlock6740); 

                        	newLeafNode(otherlv_28, grammarAccess.getBlockAccess().getProceduresKeyword_12_0());
                        
                    otherlv_29=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleBlock6752); 

                        	newLeafNode(otherlv_29, grammarAccess.getBlockAccess().getLeftCurlyBracketKeyword_12_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3297:1: ( (lv_procedures_30_0= ruleProcedure ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3298:1: (lv_procedures_30_0= ruleProcedure )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3298:1: (lv_procedures_30_0= ruleProcedure )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3299:3: lv_procedures_30_0= ruleProcedure
                    {
                     
                    	        newCompositeNode(grammarAccess.getBlockAccess().getProceduresProcedureParserRuleCall_12_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleProcedure_in_ruleBlock6773);
                    lv_procedures_30_0=ruleProcedure();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	        }
                           		add(
                           			current, 
                           			"procedures",
                            		lv_procedures_30_0, 
                            		"Procedure");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3315:2: (otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) ) )*
                    loop95:
                    do {
                        int alt95=2;
                        int LA95_0 = input.LA(1);

                        if ( (LA95_0==11) ) {
                            alt95=1;
                        }


                        switch (alt95) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3315:4: otherlv_31= ',' ( (lv_procedures_32_0= ruleProcedure ) )
                    	    {
                    	    otherlv_31=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleBlock6786); 

                    	        	newLeafNode(otherlv_31, grammarAccess.getBlockAccess().getCommaKeyword_12_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3319:1: ( (lv_procedures_32_0= ruleProcedure ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3320:1: (lv_procedures_32_0= ruleProcedure )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3320:1: (lv_procedures_32_0= ruleProcedure )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3321:3: lv_procedures_32_0= ruleProcedure
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getBlockAccess().getProceduresProcedureParserRuleCall_12_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleProcedure_in_ruleBlock6807);
                    	    lv_procedures_32_0=ruleProcedure();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getBlockRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"procedures",
                    	            		lv_procedures_32_0, 
                    	            		"Procedure");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop95;
                        }
                    } while (true);

                    otherlv_33=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleBlock6821); 

                        	newLeafNode(otherlv_33, grammarAccess.getBlockAccess().getRightCurlyBracketKeyword_12_4());
                        

                    }
                    break;

            }

            otherlv_34=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleBlock6835); 

                	newLeafNode(otherlv_34, grammarAccess.getBlockAccess().getRightCurlyBracketKeyword_13());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBlock"


    // $ANTLR start "entryRuleBusinessProcedure"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3353:1: entryRuleBusinessProcedure returns [EObject current=null] : iv_ruleBusinessProcedure= ruleBusinessProcedure EOF ;
    public final EObject entryRuleBusinessProcedure() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBusinessProcedure = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3354:2: (iv_ruleBusinessProcedure= ruleBusinessProcedure EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3355:2: iv_ruleBusinessProcedure= ruleBusinessProcedure EOF
            {
             newCompositeNode(grammarAccess.getBusinessProcedureRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleBusinessProcedure_in_entryRuleBusinessProcedure6871);
            iv_ruleBusinessProcedure=ruleBusinessProcedure();

            state._fsp--;

             current =iv_ruleBusinessProcedure; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleBusinessProcedure6881); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBusinessProcedure"


    // $ANTLR start "ruleBusinessProcedure"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3362:1: ruleBusinessProcedure returns [EObject current=null] : ( () otherlv_1= 'BusinessProcedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' ) ;
    public final EObject ruleBusinessProcedure() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        Token otherlv_21=null;
        Token otherlv_22=null;
        Token otherlv_23=null;
        Token otherlv_25=null;
        Token otherlv_27=null;
        Token otherlv_28=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_description_5_0 = null;

        AntlrDatatypeRuleToken lv_executable_7_0 = null;

        Enumerator lv_join_9_0 = null;

        Enumerator lv_split_11_0 = null;

        AntlrDatatypeRuleToken lv_capacity_13_0 = null;

        Enumerator lv_executionMode_15_0 = null;

        EObject lv_outputLinks_24_0 = null;

        EObject lv_outputLinks_26_0 = null;


         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3365:28: ( ( () otherlv_1= 'BusinessProcedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3366:1: ( () otherlv_1= 'BusinessProcedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3366:1: ( () otherlv_1= 'BusinessProcedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3366:2: () otherlv_1= 'BusinessProcedure' ( (lv_name_2_0= ruleEString ) ) otherlv_3= '{' (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )? (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )? (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )? (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )? (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )? (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )? (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )? (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )? otherlv_28= '}'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3366:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3367:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getBusinessProcedureAccess().getBusinessProcedureAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,50,FollowSets000.FOLLOW_50_in_ruleBusinessProcedure6927); 

                	newLeafNode(otherlv_1, grammarAccess.getBusinessProcedureAccess().getBusinessProcedureKeyword_1());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3376:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3377:1: (lv_name_2_0= ruleEString )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3377:1: (lv_name_2_0= ruleEString )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3378:3: lv_name_2_0= ruleEString
            {
             
            	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getNameEStringParserRuleCall_2_0()); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBusinessProcedure6948);
            lv_name_2_0=ruleEString();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_2_0, 
                    		"EString");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            otherlv_3=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleBusinessProcedure6960); 

                	newLeafNode(otherlv_3, grammarAccess.getBusinessProcedureAccess().getLeftCurlyBracketKeyword_3());
                
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3398:1: (otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==15) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3398:3: otherlv_4= 'description' ( (lv_description_5_0= ruleEString ) )
                    {
                    otherlv_4=(Token)match(input,15,FollowSets000.FOLLOW_15_in_ruleBusinessProcedure6973); 

                        	newLeafNode(otherlv_4, grammarAccess.getBusinessProcedureAccess().getDescriptionKeyword_4_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3402:1: ( (lv_description_5_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3403:1: (lv_description_5_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3403:1: (lv_description_5_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3404:3: lv_description_5_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getDescriptionEStringParserRuleCall_4_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBusinessProcedure6994);
                    lv_description_5_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	        }
                           		set(
                           			current, 
                           			"description",
                            		lv_description_5_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3420:4: (otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) ) )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==40) ) {
                alt98=1;
            }
            switch (alt98) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3420:6: otherlv_6= 'executable' ( (lv_executable_7_0= ruleEString ) )
                    {
                    otherlv_6=(Token)match(input,40,FollowSets000.FOLLOW_40_in_ruleBusinessProcedure7009); 

                        	newLeafNode(otherlv_6, grammarAccess.getBusinessProcedureAccess().getExecutableKeyword_5_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3424:1: ( (lv_executable_7_0= ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3425:1: (lv_executable_7_0= ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3425:1: (lv_executable_7_0= ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3426:3: lv_executable_7_0= ruleEString
                    {
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getExecutableEStringParserRuleCall_5_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBusinessProcedure7030);
                    lv_executable_7_0=ruleEString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	        }
                           		set(
                           			current, 
                           			"executable",
                            		lv_executable_7_0, 
                            		"EString");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3442:4: (otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) ) )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==41) ) {
                alt99=1;
            }
            switch (alt99) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3442:6: otherlv_8= 'join' ( (lv_join_9_0= ruleJoinType ) )
                    {
                    otherlv_8=(Token)match(input,41,FollowSets000.FOLLOW_41_in_ruleBusinessProcedure7045); 

                        	newLeafNode(otherlv_8, grammarAccess.getBusinessProcedureAccess().getJoinKeyword_6_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3446:1: ( (lv_join_9_0= ruleJoinType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3447:1: (lv_join_9_0= ruleJoinType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3447:1: (lv_join_9_0= ruleJoinType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3448:3: lv_join_9_0= ruleJoinType
                    {
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getJoinJoinTypeEnumRuleCall_6_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleJoinType_in_ruleBusinessProcedure7066);
                    lv_join_9_0=ruleJoinType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	        }
                           		set(
                           			current, 
                           			"join",
                            		lv_join_9_0, 
                            		"JoinType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3464:4: (otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==42) ) {
                alt100=1;
            }
            switch (alt100) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3464:6: otherlv_10= 'split' ( (lv_split_11_0= ruleSplitType ) )
                    {
                    otherlv_10=(Token)match(input,42,FollowSets000.FOLLOW_42_in_ruleBusinessProcedure7081); 

                        	newLeafNode(otherlv_10, grammarAccess.getBusinessProcedureAccess().getSplitKeyword_7_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3468:1: ( (lv_split_11_0= ruleSplitType ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3469:1: (lv_split_11_0= ruleSplitType )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3469:1: (lv_split_11_0= ruleSplitType )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3470:3: lv_split_11_0= ruleSplitType
                    {
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getSplitSplitTypeEnumRuleCall_7_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSplitType_in_ruleBusinessProcedure7102);
                    lv_split_11_0=ruleSplitType();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	        }
                           		set(
                           			current, 
                           			"split",
                            		lv_split_11_0, 
                            		"SplitType");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3486:4: (otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==16) ) {
                alt101=1;
            }
            switch (alt101) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3486:6: otherlv_12= 'capacity' ( (lv_capacity_13_0= ruleEInt ) )
                    {
                    otherlv_12=(Token)match(input,16,FollowSets000.FOLLOW_16_in_ruleBusinessProcedure7117); 

                        	newLeafNode(otherlv_12, grammarAccess.getBusinessProcedureAccess().getCapacityKeyword_8_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3490:1: ( (lv_capacity_13_0= ruleEInt ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3491:1: (lv_capacity_13_0= ruleEInt )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3491:1: (lv_capacity_13_0= ruleEInt )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3492:3: lv_capacity_13_0= ruleEInt
                    {
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getCapacityEIntParserRuleCall_8_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleBusinessProcedure7138);
                    lv_capacity_13_0=ruleEInt();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	        }
                           		set(
                           			current, 
                           			"capacity",
                            		lv_capacity_13_0, 
                            		"EInt");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3508:4: (otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==43) ) {
                alt102=1;
            }
            switch (alt102) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3508:6: otherlv_14= 'executionMode' ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    {
                    otherlv_14=(Token)match(input,43,FollowSets000.FOLLOW_43_in_ruleBusinessProcedure7153); 

                        	newLeafNode(otherlv_14, grammarAccess.getBusinessProcedureAccess().getExecutionModeKeyword_9_0());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3512:1: ( (lv_executionMode_15_0= ruleExecutionMode ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3513:1: (lv_executionMode_15_0= ruleExecutionMode )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3513:1: (lv_executionMode_15_0= ruleExecutionMode )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3514:3: lv_executionMode_15_0= ruleExecutionMode
                    {
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getExecutionModeExecutionModeEnumRuleCall_9_1_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleExecutionMode_in_ruleBusinessProcedure7174);
                    lv_executionMode_15_0=ruleExecutionMode();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	        }
                           		set(
                           			current, 
                           			"executionMode",
                            		lv_executionMode_15_0, 
                            		"ExecutionMode");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3530:4: (otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')' )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==25) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3530:6: otherlv_16= 'inputLinks' otherlv_17= '(' ( ( ruleEString ) ) (otherlv_19= ',' ( ( ruleEString ) ) )* otherlv_21= ')'
                    {
                    otherlv_16=(Token)match(input,25,FollowSets000.FOLLOW_25_in_ruleBusinessProcedure7189); 

                        	newLeafNode(otherlv_16, grammarAccess.getBusinessProcedureAccess().getInputLinksKeyword_10_0());
                        
                    otherlv_17=(Token)match(input,26,FollowSets000.FOLLOW_26_in_ruleBusinessProcedure7201); 

                        	newLeafNode(otherlv_17, grammarAccess.getBusinessProcedureAccess().getLeftParenthesisKeyword_10_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3538:1: ( ( ruleEString ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3539:1: ( ruleEString )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3539:1: ( ruleEString )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3540:3: ruleEString
                    {

                    			if (current==null) {
                    	            current = createModelElement(grammarAccess.getBusinessProcedureRule());
                    	        }
                            
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getInputLinksLinkCrossReference_10_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBusinessProcedure7224);
                    ruleEString();

                    state._fsp--;

                     
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3553:2: (otherlv_19= ',' ( ( ruleEString ) ) )*
                    loop103:
                    do {
                        int alt103=2;
                        int LA103_0 = input.LA(1);

                        if ( (LA103_0==11) ) {
                            alt103=1;
                        }


                        switch (alt103) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3553:4: otherlv_19= ',' ( ( ruleEString ) )
                    	    {
                    	    otherlv_19=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleBusinessProcedure7237); 

                    	        	newLeafNode(otherlv_19, grammarAccess.getBusinessProcedureAccess().getCommaKeyword_10_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3557:1: ( ( ruleEString ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3558:1: ( ruleEString )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3558:1: ( ruleEString )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3559:3: ruleEString
                    	    {

                    	    			if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getBusinessProcedureRule());
                    	    	        }
                    	            
                    	     
                    	    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getInputLinksLinkCrossReference_10_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleBusinessProcedure7260);
                    	    ruleEString();

                    	    state._fsp--;

                    	     
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop103;
                        }
                    } while (true);

                    otherlv_21=(Token)match(input,27,FollowSets000.FOLLOW_27_in_ruleBusinessProcedure7274); 

                        	newLeafNode(otherlv_21, grammarAccess.getBusinessProcedureAccess().getRightParenthesisKeyword_10_4());
                        

                    }
                    break;

            }

            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3576:3: (otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}' )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==28) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3576:5: otherlv_22= 'outputLinks' otherlv_23= '{' ( (lv_outputLinks_24_0= ruleLink ) ) (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )* otherlv_27= '}'
                    {
                    otherlv_22=(Token)match(input,28,FollowSets000.FOLLOW_28_in_ruleBusinessProcedure7289); 

                        	newLeafNode(otherlv_22, grammarAccess.getBusinessProcedureAccess().getOutputLinksKeyword_11_0());
                        
                    otherlv_23=(Token)match(input,14,FollowSets000.FOLLOW_14_in_ruleBusinessProcedure7301); 

                        	newLeafNode(otherlv_23, grammarAccess.getBusinessProcedureAccess().getLeftCurlyBracketKeyword_11_1());
                        
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3584:1: ( (lv_outputLinks_24_0= ruleLink ) )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3585:1: (lv_outputLinks_24_0= ruleLink )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3585:1: (lv_outputLinks_24_0= ruleLink )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3586:3: lv_outputLinks_24_0= ruleLink
                    {
                     
                    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getOutputLinksLinkParserRuleCall_11_2_0()); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleBusinessProcedure7322);
                    lv_outputLinks_24_0=ruleLink();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	        }
                           		add(
                           			current, 
                           			"outputLinks",
                            		lv_outputLinks_24_0, 
                            		"Link");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3602:2: (otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) ) )*
                    loop105:
                    do {
                        int alt105=2;
                        int LA105_0 = input.LA(1);

                        if ( (LA105_0==11) ) {
                            alt105=1;
                        }


                        switch (alt105) {
                    	case 1 :
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3602:4: otherlv_25= ',' ( (lv_outputLinks_26_0= ruleLink ) )
                    	    {
                    	    otherlv_25=(Token)match(input,11,FollowSets000.FOLLOW_11_in_ruleBusinessProcedure7335); 

                    	        	newLeafNode(otherlv_25, grammarAccess.getBusinessProcedureAccess().getCommaKeyword_11_3_0());
                    	        
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3606:1: ( (lv_outputLinks_26_0= ruleLink ) )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3607:1: (lv_outputLinks_26_0= ruleLink )
                    	    {
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3607:1: (lv_outputLinks_26_0= ruleLink )
                    	    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3608:3: lv_outputLinks_26_0= ruleLink
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getBusinessProcedureAccess().getOutputLinksLinkParserRuleCall_11_3_1_0()); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleLink_in_ruleBusinessProcedure7356);
                    	    lv_outputLinks_26_0=ruleLink();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getBusinessProcedureRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"outputLinks",
                    	            		lv_outputLinks_26_0, 
                    	            		"Link");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop105;
                        }
                    } while (true);

                    otherlv_27=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleBusinessProcedure7370); 

                        	newLeafNode(otherlv_27, grammarAccess.getBusinessProcedureAccess().getRightCurlyBracketKeyword_11_4());
                        

                    }
                    break;

            }

            otherlv_28=(Token)match(input,20,FollowSets000.FOLLOW_20_in_ruleBusinessProcedure7384); 

                	newLeafNode(otherlv_28, grammarAccess.getBusinessProcedureAccess().getRightCurlyBracketKeyword_12());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBusinessProcedure"


    // $ANTLR start "entryRuleMetaInformation"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3640:1: entryRuleMetaInformation returns [EObject current=null] : iv_ruleMetaInformation= ruleMetaInformation EOF ;
    public final EObject entryRuleMetaInformation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetaInformation = null;


        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3641:2: (iv_ruleMetaInformation= ruleMetaInformation EOF )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3642:2: iv_ruleMetaInformation= ruleMetaInformation EOF
            {
             newCompositeNode(grammarAccess.getMetaInformationRule()); 
            pushFollow(FollowSets000.FOLLOW_ruleMetaInformation_in_entryRuleMetaInformation7420);
            iv_ruleMetaInformation=ruleMetaInformation();

            state._fsp--;

             current =iv_ruleMetaInformation; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleMetaInformation7430); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMetaInformation"


    // $ANTLR start "ruleMetaInformation"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3649:1: ruleMetaInformation returns [EObject current=null] : ( () otherlv_1= 'MetaInformation' ) ;
    public final EObject ruleMetaInformation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3652:28: ( ( () otherlv_1= 'MetaInformation' ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3653:1: ( () otherlv_1= 'MetaInformation' )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3653:1: ( () otherlv_1= 'MetaInformation' )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3653:2: () otherlv_1= 'MetaInformation'
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3653:2: ()
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3654:5: 
            {

                    current = forceCreateModelElement(
                        grammarAccess.getMetaInformationAccess().getMetaInformationAction_0(),
                        current);
                

            }

            otherlv_1=(Token)match(input,51,FollowSets000.FOLLOW_51_in_ruleMetaInformation7476); 

                	newLeafNode(otherlv_1, grammarAccess.getMetaInformationAccess().getMetaInformationKeyword_1());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMetaInformation"


    // $ANTLR start "ruleJoinType"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3671:1: ruleJoinType returns [Enumerator current=null] : ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) ) ;
    public final Enumerator ruleJoinType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3673:28: ( ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3674:1: ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3674:1: ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) )
            int alt107=3;
            switch ( input.LA(1) ) {
            case 52:
                {
                alt107=1;
                }
                break;
            case 53:
                {
                alt107=2;
                }
                break;
            case 54:
                {
                alt107=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 107, 0, input);

                throw nvae;
            }

            switch (alt107) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3674:2: (enumLiteral_0= 'AND' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3674:2: (enumLiteral_0= 'AND' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3674:4: enumLiteral_0= 'AND'
                    {
                    enumLiteral_0=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleJoinType7526); 

                            current = grammarAccess.getJoinTypeAccess().getANDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getJoinTypeAccess().getANDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3680:6: (enumLiteral_1= 'OR' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3680:6: (enumLiteral_1= 'OR' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3680:8: enumLiteral_1= 'OR'
                    {
                    enumLiteral_1=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleJoinType7543); 

                            current = grammarAccess.getJoinTypeAccess().getOREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getJoinTypeAccess().getOREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3686:6: (enumLiteral_2= 'XOR' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3686:6: (enumLiteral_2= 'XOR' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3686:8: enumLiteral_2= 'XOR'
                    {
                    enumLiteral_2=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleJoinType7560); 

                            current = grammarAccess.getJoinTypeAccess().getXOREnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getJoinTypeAccess().getXOREnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleJoinType"


    // $ANTLR start "ruleSplitType"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3696:1: ruleSplitType returns [Enumerator current=null] : ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) ) ;
    public final Enumerator ruleSplitType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3698:28: ( ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3699:1: ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3699:1: ( (enumLiteral_0= 'AND' ) | (enumLiteral_1= 'OR' ) | (enumLiteral_2= 'XOR' ) )
            int alt108=3;
            switch ( input.LA(1) ) {
            case 52:
                {
                alt108=1;
                }
                break;
            case 53:
                {
                alt108=2;
                }
                break;
            case 54:
                {
                alt108=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 108, 0, input);

                throw nvae;
            }

            switch (alt108) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3699:2: (enumLiteral_0= 'AND' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3699:2: (enumLiteral_0= 'AND' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3699:4: enumLiteral_0= 'AND'
                    {
                    enumLiteral_0=(Token)match(input,52,FollowSets000.FOLLOW_52_in_ruleSplitType7605); 

                            current = grammarAccess.getSplitTypeAccess().getANDEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getSplitTypeAccess().getANDEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3705:6: (enumLiteral_1= 'OR' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3705:6: (enumLiteral_1= 'OR' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3705:8: enumLiteral_1= 'OR'
                    {
                    enumLiteral_1=(Token)match(input,53,FollowSets000.FOLLOW_53_in_ruleSplitType7622); 

                            current = grammarAccess.getSplitTypeAccess().getOREnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getSplitTypeAccess().getOREnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3711:6: (enumLiteral_2= 'XOR' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3711:6: (enumLiteral_2= 'XOR' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3711:8: enumLiteral_2= 'XOR'
                    {
                    enumLiteral_2=(Token)match(input,54,FollowSets000.FOLLOW_54_in_ruleSplitType7639); 

                            current = grammarAccess.getSplitTypeAccess().getXOREnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getSplitTypeAccess().getXOREnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSplitType"


    // $ANTLR start "ruleExecutionMode"
    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3721:1: ruleExecutionMode returns [Enumerator current=null] : ( (enumLiteral_0= 'PARALLEL' ) | (enumLiteral_1= 'ITERATIVE' ) | (enumLiteral_2= 'STREAM' ) ) ;
    public final Enumerator ruleExecutionMode() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;

         enterRule(); 
        try {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3723:28: ( ( (enumLiteral_0= 'PARALLEL' ) | (enumLiteral_1= 'ITERATIVE' ) | (enumLiteral_2= 'STREAM' ) ) )
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3724:1: ( (enumLiteral_0= 'PARALLEL' ) | (enumLiteral_1= 'ITERATIVE' ) | (enumLiteral_2= 'STREAM' ) )
            {
            // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3724:1: ( (enumLiteral_0= 'PARALLEL' ) | (enumLiteral_1= 'ITERATIVE' ) | (enumLiteral_2= 'STREAM' ) )
            int alt109=3;
            switch ( input.LA(1) ) {
            case 55:
                {
                alt109=1;
                }
                break;
            case 56:
                {
                alt109=2;
                }
                break;
            case 57:
                {
                alt109=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 109, 0, input);

                throw nvae;
            }

            switch (alt109) {
                case 1 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3724:2: (enumLiteral_0= 'PARALLEL' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3724:2: (enumLiteral_0= 'PARALLEL' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3724:4: enumLiteral_0= 'PARALLEL'
                    {
                    enumLiteral_0=(Token)match(input,55,FollowSets000.FOLLOW_55_in_ruleExecutionMode7684); 

                            current = grammarAccess.getExecutionModeAccess().getPARALLELEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getExecutionModeAccess().getPARALLELEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3730:6: (enumLiteral_1= 'ITERATIVE' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3730:6: (enumLiteral_1= 'ITERATIVE' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3730:8: enumLiteral_1= 'ITERATIVE'
                    {
                    enumLiteral_1=(Token)match(input,56,FollowSets000.FOLLOW_56_in_ruleExecutionMode7701); 

                            current = grammarAccess.getExecutionModeAccess().getITERATIVEEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getExecutionModeAccess().getITERATIVEEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3736:6: (enumLiteral_2= 'STREAM' )
                    {
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3736:6: (enumLiteral_2= 'STREAM' )
                    // ../org.parallelj.pjl/src-gen/org/parallelj/parser/antlr/internal/InternalPjl.g:3736:8: enumLiteral_2= 'STREAM'
                    {
                    enumLiteral_2=(Token)match(input,57,FollowSets000.FOLLOW_57_in_ruleExecutionMode7718); 

                            current = grammarAccess.getExecutionModeAccess().getSTREAMEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getExecutionModeAccess().getSTREAMEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExecutionMode"

    // Delegated rules


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleSpecification_in_entryRuleSpecification75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleSpecification85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleProgram_in_ruleSpecification141 = new BitSet(new long[]{0x0000000000001802L});
        public static final BitSet FOLLOW_11_in_ruleSpecification154 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_ruleProgram_in_ruleSpecification175 = new BitSet(new long[]{0x0000000000001802L});
        public static final BitSet FOLLOW_12_in_ruleSpecification192 = new BitSet(new long[]{0x0000000000400000L});
        public static final BitSet FOLLOW_ruleMetaInformationContainer_in_ruleSpecification213 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElement_in_entryRuleElement251 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleElement261 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElement_Impl_in_ruleElement308 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleInputCondition_in_ruleElement335 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleOutputCondition_in_ruleElement362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCondition_Impl_in_ruleElement389 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleProcedure_Impl_in_ruleElement416 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleForEachLoop_in_ruleElement443 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWhileLoop_in_ruleElement470 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleHandler_in_ruleElement497 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleBlock_in_ruleElement524 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleBusinessProcedure_in_ruleElement551 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleProcedure_in_entryRuleProcedure586 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleProcedure596 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleProcedure_Impl_in_ruleProcedure643 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleForEachLoop_in_ruleProcedure670 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWhileLoop_in_ruleProcedure697 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleHandler_in_ruleProcedure724 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleBlock_in_ruleProcedure751 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleBusinessProcedure_in_ruleProcedure778 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleProgram_in_entryRuleProgram813 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleProgram823 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_13_in_ruleProgram860 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProgram881 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleProgram893 = new BitSet(new long[]{0x0000000000038000L});
        public static final BitSet FOLLOW_15_in_ruleProgram906 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProgram927 = new BitSet(new long[]{0x0000000000030000L});
        public static final BitSet FOLLOW_16_in_ruleProgram942 = new BitSet(new long[]{0x0000000400000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleProgram963 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleProgram977 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProgram1000 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleProgram1012 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProgram1035 = new BitSet(new long[]{0x0006D0C0A1380000L});
        public static final BitSet FOLLOW_19_in_ruleProgram1048 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleProgram1060 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_rulePredicate_in_ruleProgram1081 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleProgram1094 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_rulePredicate_in_ruleProgram1115 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleProgram1129 = new BitSet(new long[]{0x0006D0C0A1300000L});
        public static final BitSet FOLLOW_ruleElement_in_ruleProgram1153 = new BitSet(new long[]{0x0000000000300800L});
        public static final BitSet FOLLOW_11_in_ruleProgram1166 = new BitSet(new long[]{0x0006D0C0A1000000L});
        public static final BitSet FOLLOW_ruleElement_in_ruleProgram1187 = new BitSet(new long[]{0x0000000000300800L});
        public static final BitSet FOLLOW_21_in_ruleProgram1204 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleProgram1216 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_ruleData_in_ruleProgram1237 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleProgram1250 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_ruleData_in_ruleProgram1271 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleProgram1285 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleProgram1299 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMetaInformationContainer_in_entryRuleMetaInformationContainer1335 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleMetaInformationContainer1345 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_22_in_ruleMetaInformationContainer1391 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleMetaInformationContainer1403 = new BitSet(new long[]{0x0000000000900000L});
        public static final BitSet FOLLOW_23_in_ruleMetaInformationContainer1416 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleMetaInformationContainer1428 = new BitSet(new long[]{0x0008000000000000L});
        public static final BitSet FOLLOW_ruleMetaInformation_in_ruleMetaInformationContainer1449 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleMetaInformationContainer1462 = new BitSet(new long[]{0x0008000000000000L});
        public static final BitSet FOLLOW_ruleMetaInformation_in_ruleMetaInformationContainer1483 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleMetaInformationContainer1497 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleMetaInformationContainer1511 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString1548 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString1559 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString1599 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString1625 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleInputCondition_in_entryRuleInputCondition1670 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleInputCondition1680 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_24_in_ruleInputCondition1726 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleInputCondition1747 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleInputCondition1759 = new BitSet(new long[]{0x0000000012108000L});
        public static final BitSet FOLLOW_15_in_ruleInputCondition1772 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleInputCondition1793 = new BitSet(new long[]{0x0000000012100000L});
        public static final BitSet FOLLOW_25_in_ruleInputCondition1808 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleInputCondition1820 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleInputCondition1843 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleInputCondition1856 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleInputCondition1879 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleInputCondition1893 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleInputCondition1908 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleInputCondition1920 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleInputCondition1941 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleInputCondition1954 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleInputCondition1975 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleInputCondition1989 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleInputCondition2003 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleOutputCondition_in_entryRuleOutputCondition2039 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleOutputCondition2049 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleOutputCondition2095 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleOutputCondition2116 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleOutputCondition2128 = new BitSet(new long[]{0x0000000012108000L});
        public static final BitSet FOLLOW_15_in_ruleOutputCondition2141 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleOutputCondition2162 = new BitSet(new long[]{0x0000000012100000L});
        public static final BitSet FOLLOW_25_in_ruleOutputCondition2177 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleOutputCondition2189 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleOutputCondition2212 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleOutputCondition2225 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleOutputCondition2248 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleOutputCondition2262 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleOutputCondition2277 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleOutputCondition2289 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleOutputCondition2310 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleOutputCondition2323 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleOutputCondition2344 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleOutputCondition2358 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleOutputCondition2372 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_rulePredicate_in_entryRulePredicate2408 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRulePredicate2418 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_30_in_rulePredicate2464 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_rulePredicate2485 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_rulePredicate2497 = new BitSet(new long[]{0x0000000000108000L});
        public static final BitSet FOLLOW_15_in_rulePredicate2510 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_rulePredicate2531 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_rulePredicate2545 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElement_Impl_in_entryRuleElement_Impl2581 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleElement_Impl2591 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_31_in_ruleElement_Impl2637 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElement_Impl2658 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElement_Impl2670 = new BitSet(new long[]{0x0000000012108000L});
        public static final BitSet FOLLOW_15_in_ruleElement_Impl2683 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElement_Impl2704 = new BitSet(new long[]{0x0000000012100000L});
        public static final BitSet FOLLOW_25_in_ruleElement_Impl2719 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleElement_Impl2731 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElement_Impl2754 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleElement_Impl2767 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElement_Impl2790 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleElement_Impl2804 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleElement_Impl2819 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleElement_Impl2831 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleElement_Impl2852 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleElement_Impl2865 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleElement_Impl2886 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleElement_Impl2900 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleElement_Impl2914 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleData_in_entryRuleData2950 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleData2960 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_32_in_ruleData3006 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleData3027 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleData3039 = new BitSet(new long[]{0x0000000200108000L});
        public static final BitSet FOLLOW_15_in_ruleData3052 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleData3073 = new BitSet(new long[]{0x0000000200100000L});
        public static final BitSet FOLLOW_33_in_ruleData3088 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleData3109 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleData3123 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt3160 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt3171 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_34_in_ruleEInt3210 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt3227 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleLink_in_entryRuleLink3272 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleLink3282 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_35_in_ruleLink3319 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLink3340 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleLink3352 = new BitSet(new long[]{0x0000003000008000L});
        public static final BitSet FOLLOW_15_in_ruleLink3365 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLink3386 = new BitSet(new long[]{0x0000003000000000L});
        public static final BitSet FOLLOW_36_in_ruleLink3401 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLink3424 = new BitSet(new long[]{0x0000002000000000L});
        public static final BitSet FOLLOW_37_in_ruleLink3438 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleLink3461 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleLink3473 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleCondition_Impl_in_entryRuleCondition_Impl3509 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleCondition_Impl3519 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_38_in_ruleCondition_Impl3565 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCondition_Impl3586 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleCondition_Impl3598 = new BitSet(new long[]{0x0000000012108000L});
        public static final BitSet FOLLOW_15_in_ruleCondition_Impl3611 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCondition_Impl3632 = new BitSet(new long[]{0x0000000012100000L});
        public static final BitSet FOLLOW_25_in_ruleCondition_Impl3647 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleCondition_Impl3659 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCondition_Impl3682 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleCondition_Impl3695 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleCondition_Impl3718 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleCondition_Impl3732 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleCondition_Impl3747 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleCondition_Impl3759 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleCondition_Impl3780 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleCondition_Impl3793 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleCondition_Impl3814 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleCondition_Impl3828 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleCondition_Impl3842 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleProcedure_Impl_in_entryRuleProcedure_Impl3878 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleProcedure_Impl3888 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_39_in_ruleProcedure_Impl3934 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProcedure_Impl3955 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleProcedure_Impl3967 = new BitSet(new long[]{0x00000F0012118000L});
        public static final BitSet FOLLOW_15_in_ruleProcedure_Impl3980 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProcedure_Impl4001 = new BitSet(new long[]{0x00000F0012110000L});
        public static final BitSet FOLLOW_40_in_ruleProcedure_Impl4016 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProcedure_Impl4037 = new BitSet(new long[]{0x00000E0012110000L});
        public static final BitSet FOLLOW_41_in_ruleProcedure_Impl4052 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleJoinType_in_ruleProcedure_Impl4073 = new BitSet(new long[]{0x00000C0012110000L});
        public static final BitSet FOLLOW_42_in_ruleProcedure_Impl4088 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleSplitType_in_ruleProcedure_Impl4109 = new BitSet(new long[]{0x0000080012110000L});
        public static final BitSet FOLLOW_16_in_ruleProcedure_Impl4124 = new BitSet(new long[]{0x0000000400000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleProcedure_Impl4145 = new BitSet(new long[]{0x0000080012100000L});
        public static final BitSet FOLLOW_43_in_ruleProcedure_Impl4160 = new BitSet(new long[]{0x0380000000000000L});
        public static final BitSet FOLLOW_ruleExecutionMode_in_ruleProcedure_Impl4181 = new BitSet(new long[]{0x0000000012100000L});
        public static final BitSet FOLLOW_25_in_ruleProcedure_Impl4196 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleProcedure_Impl4208 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProcedure_Impl4231 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleProcedure_Impl4244 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleProcedure_Impl4267 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleProcedure_Impl4281 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleProcedure_Impl4296 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleProcedure_Impl4308 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleProcedure_Impl4329 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleProcedure_Impl4342 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleProcedure_Impl4363 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleProcedure_Impl4377 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleProcedure_Impl4391 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleForEachLoop_in_entryRuleForEachLoop4427 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleForEachLoop4437 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_44_in_ruleForEachLoop4474 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleForEachLoop4495 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleForEachLoop4507 = new BitSet(new long[]{0x00002F0002018000L});
        public static final BitSet FOLLOW_15_in_ruleForEachLoop4520 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleForEachLoop4541 = new BitSet(new long[]{0x00002F0002010000L});
        public static final BitSet FOLLOW_40_in_ruleForEachLoop4556 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleForEachLoop4577 = new BitSet(new long[]{0x00002E0002010000L});
        public static final BitSet FOLLOW_41_in_ruleForEachLoop4592 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleJoinType_in_ruleForEachLoop4613 = new BitSet(new long[]{0x00002C0002010000L});
        public static final BitSet FOLLOW_42_in_ruleForEachLoop4628 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleSplitType_in_ruleForEachLoop4649 = new BitSet(new long[]{0x0000280002010000L});
        public static final BitSet FOLLOW_16_in_ruleForEachLoop4664 = new BitSet(new long[]{0x0000000400000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleForEachLoop4685 = new BitSet(new long[]{0x0000280002000000L});
        public static final BitSet FOLLOW_43_in_ruleForEachLoop4700 = new BitSet(new long[]{0x0380000000000000L});
        public static final BitSet FOLLOW_ruleExecutionMode_in_ruleForEachLoop4721 = new BitSet(new long[]{0x0000200002000000L});
        public static final BitSet FOLLOW_25_in_ruleForEachLoop4736 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleForEachLoop4748 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleForEachLoop4771 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleForEachLoop4784 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleForEachLoop4807 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleForEachLoop4821 = new BitSet(new long[]{0x0000200000000000L});
        public static final BitSet FOLLOW_45_in_ruleForEachLoop4835 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleForEachLoop4858 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleForEachLoop4871 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleForEachLoop4883 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleForEachLoop4904 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleForEachLoop4917 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleForEachLoop4938 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleForEachLoop4952 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleForEachLoop4966 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleWhileLoop_in_entryRuleWhileLoop5002 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleWhileLoop5012 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_46_in_ruleWhileLoop5049 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWhileLoop5070 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleWhileLoop5082 = new BitSet(new long[]{0x00000F1002018000L});
        public static final BitSet FOLLOW_15_in_ruleWhileLoop5095 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWhileLoop5116 = new BitSet(new long[]{0x00000F1002010000L});
        public static final BitSet FOLLOW_40_in_ruleWhileLoop5131 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWhileLoop5152 = new BitSet(new long[]{0x00000E1002010000L});
        public static final BitSet FOLLOW_41_in_ruleWhileLoop5167 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleJoinType_in_ruleWhileLoop5188 = new BitSet(new long[]{0x00000C1002010000L});
        public static final BitSet FOLLOW_42_in_ruleWhileLoop5203 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleSplitType_in_ruleWhileLoop5224 = new BitSet(new long[]{0x0000081002010000L});
        public static final BitSet FOLLOW_16_in_ruleWhileLoop5239 = new BitSet(new long[]{0x0000000400000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleWhileLoop5260 = new BitSet(new long[]{0x0000081002000000L});
        public static final BitSet FOLLOW_43_in_ruleWhileLoop5275 = new BitSet(new long[]{0x0380000000000000L});
        public static final BitSet FOLLOW_ruleExecutionMode_in_ruleWhileLoop5296 = new BitSet(new long[]{0x0000001002000000L});
        public static final BitSet FOLLOW_25_in_ruleWhileLoop5311 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleWhileLoop5323 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWhileLoop5346 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleWhileLoop5359 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWhileLoop5382 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleWhileLoop5396 = new BitSet(new long[]{0x0000001000000000L});
        public static final BitSet FOLLOW_36_in_ruleWhileLoop5410 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleWhileLoop5433 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleWhileLoop5446 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleWhileLoop5458 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleWhileLoop5479 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleWhileLoop5492 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleWhileLoop5513 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleWhileLoop5527 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleWhileLoop5541 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleHandler_in_entryRuleHandler5577 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleHandler5587 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_47_in_ruleHandler5633 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleHandler5654 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleHandler5666 = new BitSet(new long[]{0x00010F0012118000L});
        public static final BitSet FOLLOW_15_in_ruleHandler5679 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleHandler5700 = new BitSet(new long[]{0x00010F0012110000L});
        public static final BitSet FOLLOW_40_in_ruleHandler5715 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleHandler5736 = new BitSet(new long[]{0x00010E0012110000L});
        public static final BitSet FOLLOW_41_in_ruleHandler5751 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleJoinType_in_ruleHandler5772 = new BitSet(new long[]{0x00010C0012110000L});
        public static final BitSet FOLLOW_42_in_ruleHandler5787 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleSplitType_in_ruleHandler5808 = new BitSet(new long[]{0x0001080012110000L});
        public static final BitSet FOLLOW_16_in_ruleHandler5823 = new BitSet(new long[]{0x0000000400000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleHandler5844 = new BitSet(new long[]{0x0001080012100000L});
        public static final BitSet FOLLOW_43_in_ruleHandler5859 = new BitSet(new long[]{0x0380000000000000L});
        public static final BitSet FOLLOW_ruleExecutionMode_in_ruleHandler5880 = new BitSet(new long[]{0x0001000012100000L});
        public static final BitSet FOLLOW_25_in_ruleHandler5895 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleHandler5907 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleHandler5930 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleHandler5943 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleHandler5966 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleHandler5980 = new BitSet(new long[]{0x0001000010100000L});
        public static final BitSet FOLLOW_48_in_ruleHandler5995 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleHandler6007 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleHandler6030 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleHandler6043 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleHandler6066 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleHandler6080 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleHandler6095 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleHandler6107 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleHandler6128 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleHandler6141 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleHandler6162 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleHandler6176 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleHandler6190 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleBlock_in_entryRuleBlock6226 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleBlock6236 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_49_in_ruleBlock6282 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBlock6303 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleBlock6315 = new BitSet(new long[]{0x00010F0012118000L});
        public static final BitSet FOLLOW_15_in_ruleBlock6328 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBlock6349 = new BitSet(new long[]{0x00010F0012110000L});
        public static final BitSet FOLLOW_40_in_ruleBlock6364 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBlock6385 = new BitSet(new long[]{0x00010E0012110000L});
        public static final BitSet FOLLOW_41_in_ruleBlock6400 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleJoinType_in_ruleBlock6421 = new BitSet(new long[]{0x00010C0012110000L});
        public static final BitSet FOLLOW_42_in_ruleBlock6436 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleSplitType_in_ruleBlock6457 = new BitSet(new long[]{0x0001080012110000L});
        public static final BitSet FOLLOW_16_in_ruleBlock6472 = new BitSet(new long[]{0x0000000400000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleBlock6493 = new BitSet(new long[]{0x0001080012100000L});
        public static final BitSet FOLLOW_43_in_ruleBlock6508 = new BitSet(new long[]{0x0380000000000000L});
        public static final BitSet FOLLOW_ruleExecutionMode_in_ruleBlock6529 = new BitSet(new long[]{0x0001000012100000L});
        public static final BitSet FOLLOW_25_in_ruleBlock6544 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleBlock6556 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBlock6579 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleBlock6592 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBlock6615 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleBlock6629 = new BitSet(new long[]{0x0001000010100000L});
        public static final BitSet FOLLOW_28_in_ruleBlock6644 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleBlock6656 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleBlock6677 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleBlock6690 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleBlock6711 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleBlock6725 = new BitSet(new long[]{0x0001000000100000L});
        public static final BitSet FOLLOW_48_in_ruleBlock6740 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleBlock6752 = new BitSet(new long[]{0x0006D0C0A1000000L});
        public static final BitSet FOLLOW_ruleProcedure_in_ruleBlock6773 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleBlock6786 = new BitSet(new long[]{0x0006D0C0A1000000L});
        public static final BitSet FOLLOW_ruleProcedure_in_ruleBlock6807 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleBlock6821 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleBlock6835 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleBusinessProcedure_in_entryRuleBusinessProcedure6871 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleBusinessProcedure6881 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_50_in_ruleBusinessProcedure6927 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBusinessProcedure6948 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleBusinessProcedure6960 = new BitSet(new long[]{0x00000F0012118000L});
        public static final BitSet FOLLOW_15_in_ruleBusinessProcedure6973 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBusinessProcedure6994 = new BitSet(new long[]{0x00000F0012110000L});
        public static final BitSet FOLLOW_40_in_ruleBusinessProcedure7009 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBusinessProcedure7030 = new BitSet(new long[]{0x00000E0012110000L});
        public static final BitSet FOLLOW_41_in_ruleBusinessProcedure7045 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleJoinType_in_ruleBusinessProcedure7066 = new BitSet(new long[]{0x00000C0012110000L});
        public static final BitSet FOLLOW_42_in_ruleBusinessProcedure7081 = new BitSet(new long[]{0x0070000000000000L});
        public static final BitSet FOLLOW_ruleSplitType_in_ruleBusinessProcedure7102 = new BitSet(new long[]{0x0000080012110000L});
        public static final BitSet FOLLOW_16_in_ruleBusinessProcedure7117 = new BitSet(new long[]{0x0000000400000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleBusinessProcedure7138 = new BitSet(new long[]{0x0000080012100000L});
        public static final BitSet FOLLOW_43_in_ruleBusinessProcedure7153 = new BitSet(new long[]{0x0380000000000000L});
        public static final BitSet FOLLOW_ruleExecutionMode_in_ruleBusinessProcedure7174 = new BitSet(new long[]{0x0000000012100000L});
        public static final BitSet FOLLOW_25_in_ruleBusinessProcedure7189 = new BitSet(new long[]{0x0000000004000000L});
        public static final BitSet FOLLOW_26_in_ruleBusinessProcedure7201 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBusinessProcedure7224 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_11_in_ruleBusinessProcedure7237 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleBusinessProcedure7260 = new BitSet(new long[]{0x0000000008000800L});
        public static final BitSet FOLLOW_27_in_ruleBusinessProcedure7274 = new BitSet(new long[]{0x0000000010100000L});
        public static final BitSet FOLLOW_28_in_ruleBusinessProcedure7289 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleBusinessProcedure7301 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleBusinessProcedure7322 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_11_in_ruleBusinessProcedure7335 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_ruleLink_in_ruleBusinessProcedure7356 = new BitSet(new long[]{0x0000000000100800L});
        public static final BitSet FOLLOW_20_in_ruleBusinessProcedure7370 = new BitSet(new long[]{0x0000000000100000L});
        public static final BitSet FOLLOW_20_in_ruleBusinessProcedure7384 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMetaInformation_in_entryRuleMetaInformation7420 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleMetaInformation7430 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_51_in_ruleMetaInformation7476 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_52_in_ruleJoinType7526 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleJoinType7543 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_54_in_ruleJoinType7560 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_52_in_ruleSplitType7605 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleSplitType7622 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_54_in_ruleSplitType7639 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_55_in_ruleExecutionMode7684 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_56_in_ruleExecutionMode7701 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_57_in_ruleExecutionMode7718 = new BitSet(new long[]{0x0000000000000002L});
    }


}