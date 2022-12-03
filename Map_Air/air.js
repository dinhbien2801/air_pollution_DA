function ChangeToAirLayer() {
    require([
        "esri/Map",
        "esri/views/MapView",
        "esri/layers/FeatureLayer",
        "esri/renderers/ClassBreaksRenderer"
    ], function (Map, MapView, FeatureLayer, ClassBreaksRenderer) {

        var map = new Map({
            basemap: "topo-vector"
        });

        var view = new MapView({
            container: "viewDiv",
            map: map,
            center: [105.77373 , 21.11737],       
            zoom: 15
        });
        var airRenderer = new ClassBreaksRenderer({
            type: "class-breaks",
            field: "value"
        });

        airRenderer.addClassBreakInfo({
            minValue: 0.001,
            maxValue: 50.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(34, 204, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        airRenderer.addClassBreakInfo({
            minValue: 50.001,
            maxValue: 100.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(225, 227, 96, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        airRenderer.addClassBreakInfo({
            minValue: 100.001,
            maxValue: 150.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255, 153, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        airRenderer.addClassBreakInfo({
            minValue: 150.001,
            maxValue: 200.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255,0,0,0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        airRenderer.addClassBreakInfo({
            minValue: 200.001,
            maxValue: 300.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(215, 52, 180, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        airRenderer.addClassBreakInfo({
            minValue: 300.001,
            maxValue: 9999999999.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(132, 32, 32, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });
        // var airLayerUrl = "https://services6.arcgis.com/Gq7ZVDxoNTRnBE4W/arcgis/rest/services/air_pollution/FeatureServer";
        var airLayerUrl = "https://services8.arcgis.com/gfbPq2TJv9V7MYhr/arcgis/rest/services/air/FeatureServer/0";  
     
        var pollutants = new FeatureLayer({
            url: airLayerUrl,
            renderer: airRenderer,
            // labelingInfo: [pollutantLabels]
        });
        map.add(pollutants);
    });
    var range1 = document.getElementById("range1");
    var range2 = document.getElementById("range2");
    var range3 = document.getElementById("range3");
    var range4 = document.getElementById("range4");
    var range5 = document.getElementById("range5");
    var range6 = document.getElementById("range6");
    range1.innerHTML = "0-50";
    range2.innerHTML = "51-100";
    range3.innerHTML = "101-150";
    range4.innerHTML = "151-200";
    range5.innerHTML = "201-300";
    range6.innerHTML = "301-500";
}