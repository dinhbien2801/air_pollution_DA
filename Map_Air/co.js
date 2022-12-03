function ChangeToCOLayer() {
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

        var coRenderer = new ClassBreaksRenderer({
            type: "class-breaks",
            field: "value" // tên field trom feature của layer tương ứng
        });

        coRenderer.addClassBreakInfo({
            // minValue: 0.001,
            minValue: 0,
            maxValue: 4.50,
            symbol: {
                type: "simple-fill",
                color: "rgba(34, 204, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        coRenderer.addClassBreakInfo({
            minValue: 4.501,
            maxValue: 9.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(225, 227, 96, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        coRenderer.addClassBreakInfo({
            minValue: 9.401,
            maxValue: 12.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(255, 153, 0, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        coRenderer.addClassBreakInfo({
            minValue: 12.401,
            maxValue: 15.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(255,0,0,0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        coRenderer.addClassBreakInfo({
            minValue: 15.401,
            maxValue: 30.4,
            symbol: {
                type: "simple-fill",
                color: "rgba(215, 52, 180, 0.5)",
                outline: {
                    width: 0.1,
                    color: "rgb(255,254,253)"
                }
            }
        });

        coRenderer.addClassBreakInfo({
            minValue: 30.401,
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

        var coLayerUrl = "https://services8.arcgis.com/gfbPq2TJv9V7MYhr/arcgis/rest/services/co/FeatureServer/0";
        var pollutants = new FeatureLayer({
            url: coLayerUrl,
            renderer: coRenderer,
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
    range1.innerHTML = "0-4.4";
    range2.innerHTML = "4.5-9.4";
    range3.innerHTML = "9.5-12.4";
    range4.innerHTML = "12.5-15.4";
    range5.innerHTML = "15.5-30.4";
    range6.innerHTML = ">=30.5";
}