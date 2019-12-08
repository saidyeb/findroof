package findroof.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.util.json.Jackson;

@Service
public class AwsService {

	private Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	// "myAccessKey", "mySecretKey", "token"
	private BasicSessionCredentials credentials = 
			new BasicSessionCredentials("ASIAZZU55F677GLVRNWH", 
					"tk5n/K6+rqwtC4oIAXbWE5L4X4bDgjRmZM125ig/", 
					"FwoGZXIvYXdzEF8aDBx5loRQPK7/80Vj8CLGAS9YzvMKuenrqTNypU5oEsit7dcjgyqDSQGjy76PQTAMXSQUBAeH7pvJPZLFhfBBa+6HibyfUN4zW5fxs6qRRDSrITpv6r4cyuZY4614IVFtlGPcSlpGQWKFyM/cEZv5U9byQ6fqZmxayMhuCeN95qtn0Zfj/fJXI2FRdxlOzBOeCLs9hUw+HpBiA7AdtFtIsSxMNfKhSHd6XmgNfJzCApRVNPIcTIaLKYQjZq/EOCHBniRNDZi5cb0GaJiTjfzun5GRxy82ayj9srDvBTItx+eFyAqCVQhYTNPglaUJwyWVZY9Fdj9XJsMzJkMUp84PXJMi+myUf9pPdGdE"
	 ); 
	
	private AWSLambda client; 
	
	public AwsService() 
	{
		this.client = AWSLambdaClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_1) // US East (N. Virginia)
                .build();
	}
	
	public double getPriceDollar(double priceEuro) throws Exception
	{
		try
		{
			double priceDollar = 0.0;
			
			if(priceDollar > 0)
			{
				String paramsJson = "{ \"price\" :"+ priceEuro +"}";
				InvokeRequest req = new InvokeRequest()
				                           .withFunctionName("ConvertPricePossession")
				                           .withPayload(paramsJson);
				
				InvokeResult result = client.invoke(req);
				if (result.getStatusCode() == 200)
				{
					String resultJson = new String( result.getPayload().array(), "UTF-8" );
					priceDollar = Jackson.fromJsonString(resultJson, Double.class);
				}				
			}
			
			return priceDollar;	
		}
		catch(Exception exception)
		{
			String msg = "Erreur lors de la récupération du prix en Dollar depuis AWS function lambda avec pour paramètres '"+priceEuro+"'";
			_logger.error(msg, exception);
			return 0.0;
		}
	}
}
