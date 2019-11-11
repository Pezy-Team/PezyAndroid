package com.example.pezyandroid.louise.API;



/**
 * Created by anubi&Rookiez on 13/02/2562.
 */
public class APICallPathModel {

    /**
     * JAX-RS API
     */

    /**
     * Auth API
      */
    public static final String AUTH = "user/login";

    public static final String CASE_FIND_JOIN = "case/findbyJoin";


    /**
     * Hibernate API
     */
    public static final String API_CASE = "case/%d";

    public static final String TRACKING_DEVICE = "tracking-device/%d";

    public static final String FIND_CASE_WITH_PREP_SEQ_PLACE = "case/with-prep-seq-place/%s";

    public static final String PATCH_ESTIMATE_PRICE = "asset/%s/estimate-price/%s";

    public static final String POST_CHECK_TRACKING_DEVICE = "tracking-device/checks";

    public static final String POST_CHECK_TRACKING_DEVICE_MULTIPLE = "tracking-device/checks-multiple";

    public static final String BINDING_TRACKING_DEVICE = "prep-seq-line-tracking-device/mobile/bind-track-with-prep-seq";

    public static final String REMOVE_TRACKING_DEVICE = "prep-seq-line-tracking-device/mobile/unbinding-track-with-prep-seq/%s";

    public static final String MAKE_SEQUESTRATION = "sequester/make-sequestration/in-case-id/%s";

    public static final String TRACKING_DEVICE_AVAILABLE = "tracking-device/available";


    /**
     * Add asset
     * TODO : This is hard code url for testing, Don't forget to change these url to the real path.
     */
    public static final String ADD_ASSET_LAND = "http://103.40.146.174:8080/assettrackingweb/assetTypeLandView.action?type_id=1&case_id=1&token=1";
    public static final String ADD_ASSET_BUILDING = "http://103.40.146.174:8080/assettrackingweb/assetTypeBuildingView.action?type_id=2&case_id=1&token=1";
    public static final String ADD_ASSET_LAND_AND_BUILDING = "http://103.40.146.174:8080/assettrackingweb/assetTypeLandAndBuildingsView.action?type_id=3&case_id=1&token=1";
    public static final String ADD_ASSET_APARTMENT = "http://103.40.146.174:8080/assettrackingweb/assetTypeapartmentView.action?type_id=4&case_id=1&token=1";
    public static final String ADD_ASSET_MACHINE = "http://103.40.146.174:8080/assettrackingweb/assetTypemachineView.action?type_id=5&case_id=1&token=1";
    public static final String ADD_ASSET_BOND = "http://103.40.146.174:8080/assettrackingweb/assetTypeBondView.action?type_id=6&case_id=1&token=1";
    public static final String ADD_ASSET_GUN = "http://103.40.146.174:8080/assettrackingweb/assetTypegunView.action?type_id=7&case_id=1&token=1";
    public static final String ADD_ASSET_VEHICLE = "http://103.40.146.174:8080/assettrackingweb/assetTypeVehicleView.action?type_id=8&case_id=1&token=1";
    public static final String ADD_ASSET_SHARE_COMPANY = "http://103.40.146.174:8080/assettrackingweb/assetTypeStockCompanyView.action?type_id=9&case_id=1&token=1";
    public static final String ADD_ASSET_RENTING_RIGHT = "http://103.40.146.174:8080/assettrackingweb/assetTypeRentView.action?type_id=10&case_id=1&token=1";
    public static final String ADD_ASSET_ETC = "http://103.40.146.174:8080/assettrackingweb/assetTypeOtherView.action?type_id=11&case_id=1&token=1";

    public static final String ADD_ASSET_REPORT = "http://103.40.146.174:8080/assettrackingweb/report/rep-sequesters.jsp?id_ps=4";


}
