package com.dfhc.wk;


import java.util.List;

import org.quickbundle.project.RmGlobalReference;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class QueryDictionaryData implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		String path = args.get(0).toString();  
        String type = args.get(1).toString();
		String realPath = RmGlobalReference.get(path, type);
		return realPath;
	}

}
