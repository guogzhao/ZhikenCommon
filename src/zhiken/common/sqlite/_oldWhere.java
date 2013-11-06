package zhiken.common.sqlite;
//package zhiken.common;
//
//import java.util.List;
//
//public class SQLiteWhere {
//	private StringBuilder expression;
//	private String[] parameters;
//
//	public SQLiteWhere() {
//		super();
//	}
//
//	public SQLiteWhere(List<Param> params) {
//		super();
//		init(params);
//	}
//
//	private void init(List<Param> params) {
//		if (params != null) {
//			this.parameters = new String[params.size()];
//			Param param;
//			for (int i = 0; i < this.parameters.length; i++) {
//				param = params.get(i);
//				if (i > 0) {
//					this.expression.append(param.getAnd());
//				}
//				this.expression.append("(");
//				this.expression.append(param.getKey());
//				this.expression.append(param.getCdt());
//				this.expression.append("?)");
//				this.parameters[i] = param.getVal();
//			}
//		}
//	}
//
//	public String getExpression() {
//		if (expression != null)
//			return expression.toString();
//		return null;
//	}
//
//	public String[] getParameters() {
//		return parameters;
//	}
//}
//
//// public void setExpression(String expression) {
//// this.expression = expression;
//// }
//// public void setParameters(String[] parameters) {
//// this.parameters = parameters;
//// }