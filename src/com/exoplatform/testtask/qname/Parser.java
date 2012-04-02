package com.exoplatform.testtask.qname;

public class Parser {
	
	private final String NONSPACE_REGULAR_EXPRESSION = "[[\\w\\W]&&[^\\s/:*'\"|\\[\\]]]{1}";
	private final String STRING_REGULAR_EXPRESSION = "[[\\w\\W]&&[^/:*'\"|\\[\\]]]+";
	private final String PREFIX_REGULAR_EXPRESSION = "[_:A-Za-z][-._:A-Za-z0-9]*";
	private final String ONECHARSN_REGULAR_EXPRESSION = "[[\\w\\W]&&[^\\s/:.*'\"|\\[\\]]]{1}";
	
	public QName parse (String name) throws IllegalNameException {
		if (isName(name)){
			return new QName(name);
		}
		else
			throw new IllegalNameException();
	}
	
	private boolean isNonspace(String nonspace){
		return nonspace.matches(NONSPACE_REGULAR_EXPRESSION);
	}
	
	private boolean isChar(String ch){
		return (isNonspace(ch) || ch.matches("[ ]{1}"));
	}
	
	private boolean isString(String str) {
		return str.matches(STRING_REGULAR_EXPRESSION);
	}
	
	private boolean isPrefix(String prefix){
		return prefix.matches(PREFIX_REGULAR_EXPRESSION);
	}
	
	private boolean isThreeOrMoreCharName(String threeOrMore){
		String first, last, middle ;
		
		first = threeOrMore.substring(0, 1);
		last = threeOrMore.substring(threeOrMore.length()-1, threeOrMore.length());
		middle = threeOrMore.substring(1, threeOrMore.length()-1);
		
		return (isNonspace(first) && isNonspace(last) && isString(middle));
	}
	
	private boolean isTwoCharLocalName (String twoCharLocalName) {
		return (isNonspace(twoCharLocalName.substring(0, 1)) && isNonspace(twoCharLocalName.substring(1, 2)));		
	}
	
	private boolean isOneCharLocalName (String oneCharLocalName){
		return isNonspace(oneCharLocalName);
	}
	
	private boolean isOneCharSimpleName(String oneCharSimpleName){
		return (oneCharSimpleName.matches(ONECHARSN_REGULAR_EXPRESSION));
	}
	
	private boolean isTwoCharSimpleName(String twoChareSimpleName){
		String first, last;
		first = twoChareSimpleName.substring(0, 1);
		last = twoChareSimpleName.substring(1, 2);
		
		if( isOneCharSimpleName(first) && isOneCharSimpleName(last))
			return true;
		else if (isOneCharSimpleName(first) && last.equals(".") )
			return true;
		else if (first.equals(".") && isOneCharSimpleName(last) )
			return true;
		else
			return false;
	}
	
	private boolean isLocalName(String localName) {
		int lengthLocalName = localName.length();
		if (lengthLocalName == 0)
			return false;
		else if (lengthLocalName == 1)
			return isOneCharLocalName(localName);
		else if (lengthLocalName == 2)
			return isTwoCharLocalName(localName);
		else
			return isThreeOrMoreCharName(localName);
	}
	
	private boolean isPrefixedName(String prefixedName){
		//the XML's name does not symbol ':'
		int indexTwoDot;
		String prefix, localName;
		indexTwoDot = prefixedName.indexOf(':');
		if (indexTwoDot == -1)
			return false;
		prefix = prefixedName.substring(0, indexTwoDot);
		localName = prefixedName.substring(indexTwoDot + 1, prefixedName.length());
		return (isPrefix(prefix) && isLocalName(localName));
	}
	
	private boolean isSimpleName(String simpleName) {
		int lengthLocalName = simpleName.length();
		if (lengthLocalName == 0)
			return false;
		else if (lengthLocalName == 1)
			return isOneCharSimpleName(simpleName);
		else if (lengthLocalName == 2)
			return isTwoCharSimpleName(simpleName);
		else
			return isThreeOrMoreCharName(simpleName);
	}
	
	public boolean isName(String name){
		return (isSimpleName(name) || isPrefixedName(name));
	}
}
