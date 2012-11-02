package org.mule.munit.functions;

import com.google.common.base.Preconditions;
import org.mule.api.MuleContext;
import org.mule.api.el.ExpressionLanguageContext;
import org.mule.api.el.ExpressionLanguageFunction;
import org.mule.module.scripting.component.Scriptable;

import javax.script.Bindings;
import javax.script.ScriptException;


public class FlowResultFunction implements ExpressionLanguageFunction {
    private MuleContext context;

    public FlowResultFunction(MuleContext context) {
        this.context = context;
    }

    @Override
    public Object call(Object[] params, ExpressionLanguageContext context) {
        if (params.length > 0 && params[0] instanceof String) {

            String flowName = (String) params[0];
            Object registeredScript = this.context.getRegistry().lookupObject(flowName);

            Preconditions.checkNotNull(registeredScript, "The script called " + flowName + " could not be found");
            if (registeredScript instanceof Scriptable) {
                Scriptable script = (Scriptable) registeredScript;
                Bindings bindings = script.getScriptEngine().createBindings();
                script.populateDefaultBindings(bindings);
                try {
                    return script.runScript(bindings);
                } catch (ScriptException e) {
                    throw new RuntimeException("Your script has an execution error ",e );
                }
            }
        }
        throw new IllegalArgumentException("The script name references a non script component, make sure the script is written as in " +
                "http://www.mulesoft.org/documentation/display/MULE3USER/Scripting+Module+Reference ");
    }
}
