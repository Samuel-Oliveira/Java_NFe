package br.com.samuelweb.nfe.util;

import java.util.Collection;

public final class ObjetoUtil {
	
		private ObjetoUtil(){
			super();
		}

		public static Boolean equalsNull(Object object ){
			return object == null;
		}
		
		public static Boolean differentNull(Object object){
			return object != null; 
		}
		

		/**
		 * Verifica se um objeto &eacute; vazio.
		 * 
		 * @param obj
		 * @return <b>true</b> se o objeto for vazio(empty).
		 */
	    public static boolean isEmpty(Object obj) {
			if (obj == null)
				return true;
			if (obj instanceof Collection)
				return ((Collection<?>) obj).size() == 0;

			final String s = String.valueOf(obj).trim();

			return s.length() == 0 || s.equalsIgnoreCase("null");
		}
		
		@SuppressWarnings("rawtypes")
		public static Boolean equalsNull(Collection collection){
			return collection == null;
		}
		
		@SuppressWarnings("rawtypes")
		public static Boolean differentNull(Collection collection){
			return collection != null; 
		}
		
		@SuppressWarnings("rawtypes")
		public static Boolean isEmpty(Collection collection){
			return collection.isEmpty();
		}
		
		@SuppressWarnings("rawtypes")
		public static Boolean isNotEmpty(Collection collection){
			return !collection.isEmpty();
		}

}
