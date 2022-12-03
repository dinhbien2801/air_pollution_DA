function ChangeToNO2Layer() {
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

        var no2Renderer = new ClassBreaksRenderer({
            type: "class-breaks",
            field: "value"
        });

        no2Renderer.addClassBreakInfo({
            minValue: 0,
            maxValue: 40.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(34, 204, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        no2Renderer.addClassBreakInfo({
            minValue: 40.001,
            maxValue: 80.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(225, 227, 96, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        no2Renderer.addClassBreakInfo({
            minValue: 80.001,
            maxValue: 180.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255, 153, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        no2Renderer.addClassBreakInfo({
            minValue: 180.001,
            maxValue: 280.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255,0,0,0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        no2Renderer.addClassBreakInfo({
            minValue: 280.001,
            maxValue: 400.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(215, 52, 180, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        no2Renderer.addClassBreakInfo({
            minValue: 400.001,
            maxValue: 9999999999.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(165,42,42,0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        var no2LayerUrl = "https://services8.arcgis.com/gfbPq2TJv9V7MYhr/arcgis/rest/services/no2/FeatureServer/0";
        var pollutants = new FeatureLayer({
            url: no2LayerUrl,
            renderer: no2Renderer,
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
    range1.innerHTML = "0-40";
    range2.innerHTML = "41-80";
    range3.innerHTML = "81-180";
    range4.innerHTML = "181-280";
    range5.innerHTML = "281-400";
    range6.innerHTML = ">=401";
}