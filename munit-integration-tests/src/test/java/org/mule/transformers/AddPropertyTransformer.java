/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.transformers;

import org.mule.api.MuleMessage;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractMessageTransformer;

public class AddPropertyTransformer extends AbstractMessageTransformer
{

    @Override
    public Object transformMessage(MuleMessage message, String outputEncoding) throws TransformerException
    {
        message.setInvocationProperty("newProperty", "propertyValue");
        return message;
    }
}
