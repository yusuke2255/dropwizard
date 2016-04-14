package jp.pechika;



public class SampleApplication extends Application<SampleConfiguration> {

	public static void main(String[] args) throws Exception {
		new SampleApplication().run(args);
	}
	
	@Override
    public String getName() {
        return "sample";
    }

	private final HibernateBundle<SampleConfiguration> hibernate = 
			new HibernateBundle<SampleConfiguration>(
			) {
		@Override
	    public DataSourceFactory getDataSourceFactory(SampleConfiguration configuration) {
	        return configuration.getDatabase();
	    }		
	};
	
	@Override
	public void initialize(Bootstrap<SampleConfiguration> bootstrap) {
		
		bootstrap.addBundle(new ViewBundle<SampleConfiguration>(){
			 @Override
		        public Map<String, Map<String, String>> getViewConfiguration(SampleConfiguration config) {
		            return config.getViewRendererConfiguration();
		        }
			
		});
	    bootstrap.addBundle(hibernate);
		bootstrap.addBundle(new Java8Bundle());
//        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
        bootstrap.addBundle(new MultiPartBundle());
	}
	
	
	@Override
	public void run(SampleConfiguration config, Environment environment) {
		// For use multipart
		environment.jersey().register(MultiPartFeature.class);

		ConfigData.initializeInstance(
				config.getStaticResourceBasePath(),
				config.getManageServerBasePath(),
				config.getCorporationImageUrl()
		);


	}
}
