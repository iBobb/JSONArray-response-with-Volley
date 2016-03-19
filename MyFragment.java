    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View fragmentView = inflater.inflate(R.layout.mylayout, container, false);
        
   DataClass classInstance = new DataClass();
   classInstance.downloadEvents(serviceUrl, createMyReqSuccessListener(), createMyReqErrorListener());
   //notify any adapters for the changes here if using RecyclerView for example or do it in the parse method below
   
   } // end of onCreate
   
    private Response.Listener<JSONArray> createMyReqSuccessListener() {
        return new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
            // stop the refreshing indicator if you do refreshing witha swipeRefreshLayout
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
                
                extractJsonToList(response); //method to parse the JSON response
            }
        };
    }


    private Response.ErrorListener createMyReqErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MyApplication.getAppContext(), "ERROR:" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
    }
    // extract the Json data from each object and NOTIFY ADAPTER for each item inserted in our List
       private void extractJsonToList(JSONArray result) { // result should be the response we got from our onResponse method
        try {

            for (int i = 0; i < result.length(); i++) {
                JSONObject eventJson = result.getJSONObject(i);
                
                String title = eventJson.getString("EventTitle"); // the value from your JSON
                String body = eventJson.getString("EventBody");


                DataClass myData = new DataClass(parameter1, parameter2); //parameters for the constructor of your DataClass used to create an Object of your data type
                myList.add(myData);
                rAdapter.notifyItemInserted(i); // your RecyclerView adapter for example
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
