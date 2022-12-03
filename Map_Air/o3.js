function ChangeToO3Layer() {
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

        var pollutantLabels = {
            symbol: {
                type: "text",
                color: "#000000",
                font: {
                    size: "10px",
                    family: "Noto Sans",
                    weight: "normal"
                }
            },
            labelPlacement: "above-center",
            labelExpressionInfo: {
                expression: "$feature.value"
            }
        };

        var o3Renderer = new ClassBreaksRenderer({
            type: "class-breaks",
            field: "value"
        });

        o3Renderer.addClassBreakInfo({
            minValue: 0,
            maxValue: 54.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(34, 204, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        o3Renderer.addClassBreakInfo({
            minValue: 54.001,
            maxValue:70.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(225, 227, 96, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        o3Renderer.addClassBreakInfo({
            minValue: 70.001,
            maxValue: 85.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255, 153, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        o3Renderer.addClassBreakInfo({
            minValue: 85.001,
            maxValue: 105.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(255,0,0,0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        o3Renderer.addClassBreakInfo({
            minValue: 105.001,
            maxValue: 200.0,
            symbol: {
                type: "simple-fill",
                color: "rgba(215, 52, 180, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        o3Renderer.addClassBreakInfo({
            minValue: 200.001,
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
        var o3LayerUrl = "https://services8.arcgis.com/gfbPq2TJv9V7MYhr/arcgis/rest/services/o3/FeatureServer/0";
        
       
        var pollutants = new FeatureLayer({
            url: o3LayerUrl,
            renderer: o3Renderer,
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
    range1.innerHTML = "0-54";
    range2.innerHTML = "54-70";
    range3.innerHTML = "71-85";
    range4.innerHTML = "86-105";
    range5.innerHTML = "106-200";
    range6.innerHTML = ">=201";
}