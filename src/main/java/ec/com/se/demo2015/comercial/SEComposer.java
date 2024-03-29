package ec.com.se.demo2015.comercial;

import java.util.Arrays;

import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyMessageComposer;

public class SEComposer extends RESTEasyMessageComposer {

	@Override
	public Message compose(RESTEasyBindingData source, Exchange exchange)
			throws Exception {
		final Message message = super.compose(source, exchange);
		if (source.getOperationName().equals("crearOrden")
				&& (source.getParameters().length == 1)) {
			message.setContent(source.getParameters()[0]);
		}
		return message;
	}

	@Override
	public RESTEasyBindingData decompose(Exchange exchange,
			RESTEasyBindingData target) throws Exception {
		// TODO Auto-generated method stub
		target.addHeader("Access-Control-Allow-Origin", Arrays.asList("*"));
		target.addHeader("Access-Control-Allow-Headers", Arrays.asList("origin,name,Content-Type,accept"));
		return super.decompose(exchange, target);
	}

}
