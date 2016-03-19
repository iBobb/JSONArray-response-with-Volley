public void downloadEvents(String urlService, Response.Listener<JSONArray> successListener, Response.ErrorListener errorListener) {
        //final List<Event> myEventsList = eventsList;
        RequestQueue requestQueue = this.volleySingleton.getRequestQueue();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, urlService, null, successListener, errorListener);
      
        requestQueue.add(jsonArrayRequest);
    }
    
  // the urlService parameter is the URL to your service(which returns a JSONArray) on your hosting.
  
