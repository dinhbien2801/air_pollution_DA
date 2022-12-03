function ChangeToPM25Layer() {
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

      
        var pm25Renderer = new ClassBreaksRenderer({
            type: "class-breaks",
            field: "value"
        });

        pm25Renderer.addClassBreakInfo({
            minValue: 0.001,
            maxValue: 12.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(34, 204, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        pm25Renderer.addClassBreakInfo({
            minValue: 12.001,
            maxValue: 35.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(225, 227, 96, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        pm25Renderer.addClassBreakInfo({
            minValue: 35.401,
            maxValue: 55.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(255, 153, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        pm25Renderer.addClassBreakInfo({
            minValue: 55.401,
            maxValue: 150.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(255,0,0,0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        pm25Renderer.addClassBreakInfo({
            minValue: 150.401,
            maxValue: 250.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(215, 52, 180, 0.5)",
                outline: {
                    width: 0.1,
                    color:"rgb(255,254,253)"
                }
            }
        });

        pm25Renderer.addClassBreakInfo({
            minValue: 250.401,
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

        var pm25LayerUrl = "https://services8.arcgis.com/gfbPq2TJv9V7MYhr/arcgis/rest/services/pm25/FeatureServer/0";
        var pollutants = new FeatureLayer({
            url: pm25LayerUrl,
            renderer: pm25Renderer,
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
    range1.innerHTML = "0-12";
    range2.innerHTML = "12.1-35.4";
    range3.innerHTML = "35.5-55.4";
    range4.innerHTML = "55.5-150.4";
    range5.innerHTML = "150.5-250.4";
    range6.innerHTML = ">=250.5";
}