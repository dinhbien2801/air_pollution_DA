function ChangeToSO2Layer() {
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

        var so2Renderer = new ClassBreaksRenderer({
            type: "class-breaks",
            field: "value"
        });

        so2Renderer.addClassBreakInfo({
            minValue: 0,
            maxValue: 35.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(34, 204, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        so2Renderer.addClassBreakInfo({
            minValue: 35.001,
            maxValue: 75.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(225, 227, 96, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        so2Renderer.addClassBreakInfo({
            minValue: 75.001,
            maxValue: 185.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255, 153, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        so2Renderer.addClassBreakInfo({
            minValue: 185.001,
            maxValue: 304.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255,0,0,0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        so2Renderer.addClassBreakInfo({
            minValue: 304.001,
            maxValue: 1249.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(215, 52, 180, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        so2Renderer.addClassBreakInfo({
            minValue: 1249.001,
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
    
        var so2LayerUrl ="https://services8.arcgis.com/gfbPq2TJv9V7MYhr/arcgis/rest/services/so2/FeatureServer/0";
        var pollutants = new FeatureLayer({
            url: so2LayerUrl,
            renderer: so2Renderer,
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
    range1.innerHTML = "0-35";
    range2.innerHTML = "36-75";
    range3.innerHTML = "76-185";
    range4.innerHTML = "186-304";
    range5.innerHTML = "305-1249";
    range6.innerHTML = ">=1250";
}