package smartcontrols.in.assignment_task.network.listeners;

import smartcontrols.in.assignment_task.network.response.ApiResponse;

/**
 * Created by Prateek on 2/14/2018.
 */

public interface ApiServiceListener {

    /*
   * Called on failure in network operation
   **/
    public void onCancelled();

    /*
    * Called before the network operation begins
    **/
    public void onPreExecute();


    /**
     * Called on completion of network operation
     *
     * @param apiResponse of the completed operation
     */
    public void onPostExecute(ApiResponse apiResponse);
}
