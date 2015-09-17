
    
    ///get home page chart
    static public func homePageView1(frame: CGRect, data: [Double]) ->LineChartView{
        
        let dataLabels = [" 0:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00",
            "23:00", "24:00"]
        
        if (dataLabels.count > data.count){
            var tempView = LineChartView(frame: frame)
            tempView.noDataText = "data should be Double type Number, 25 elements "
            return tempView
            
        }
        
        let values = data
        
        let colors = [UIColor(red: 77/255, green: 190/255, blue: 206/255, alpha: 0.9),
            UIColor(red: 150/255, green: 150/255, blue: 150/255, alpha: 0.9),
            UIColor(red: 50/255, green: 50/255, blue: 50/255, alpha: 1),]
        
        var dataEntries1: [ChartDataEntry] = []
        for i in 0..<dataLabels.count{
            
            let dataEntry1 = ChartDataEntry(value: values[i] / 1000, xIndex: i)
            dataEntries1.append(dataEntry1)
        }
        var chartView = LineChartView(frame: frame)
        
        
        let lineChartDataSet1 = LineChartDataSet(yVals: dataEntries1, label: "data 1")
        
        //在dataset里设置线的颜�?        lineChartDataSet1.colors = [colors[0]]
        
        //折线节点圆圈设置
        //不显示圆�?        lineChartDataSet1.drawCircleHoleEnabled = false
        lineChartDataSet1.circleRadius = 0
        //value设置
        lineChartDataSet1.drawValuesEnabled = false
        //线宽设置
        lineChartDataSet1.lineWidth = 1
        
        //设置填充、填充颜�?        lineChartDataSet1.drawFilledEnabled = true
        lineChartDataSet1.fillColor = colors[0]
        lineChartDataSet1.fillAlpha = 2
        
        //设置data
        
        let lineChartData = LineChartData(xVals: dataLabels, dataSet: lineChartDataSet1)
        chartView.data = lineChartData
        
        //设置动画
        chartView.animate(xAxisDuration: 2.0, yAxisDuration: 2.0, easingOption: .Linear)
        
        //设置X Y 轴及网格参数
        
        
        chartView.xAxis.labelPosition = .Bottom
        chartView.xAxis.drawGridLinesEnabled = false
        chartView.xAxis.axisLineColor = colors[2]
        chartView.xAxis.axisLineWidth = 2
        chartView.xAxis.labelTextColor = colors[2]
        
        chartView.xAxis.labelFont = UIFont.boldSystemFontOfSize(12)
        chartView.xAxis.setLabelsToSkip(5)
        
        chartView.rightAxis.enabled = false
        chartView.leftAxis.drawAxisLineEnabled = false
        chartView.leftAxis.gridColor = colors[1]
        chartView.leftAxis.gridLineWidth = 0.5
        chartView.leftAxis.setLabelCount(3, force: false)
        chartView.leftAxis.labelFont = UIFont.boldSystemFontOfSize(12)
        chartView.leftAxis.labelTextColor = colors[1]
        
        
        //value格式�?        var numberFormatter = NSNumberFormatter()
        numberFormatter.maximumFractionDigits = 1
        numberFormatter.minimumIntegerDigits = 1
        numberFormatter.positiveSuffix = "K"
        chartView.leftAxis.valueFormatter = numberFormatter
        chartView.xAxis.avoidFirstLastClippingEnabled = true
        
        //设置图片右边�?//        chartView.extraRightOffset = 15
        
        //设置图像description
        chartView.descriptionText = ""
        chartView.legend.enabled = false
        
        //图像背景
        chartView.backgroundColor = UIColor.whiteColor()
        chartView.gridBackgroundColor = UIColor.whiteColor()
        chartView.notifyDataSetChanged()
        
        //动作
        chartView.setScaleEnabled(false)
        
        // add gradient layer
        let gradientLayer = CAGradientLayer()
        gradientLayer.frame = CGRect(x: 25, y: 10, width: frame.width - 35, height: frame.height - 38)
        
        let color1 = UIColor(red: 255/255, green: 255/255, blue: 255/255, alpha: 0).CGColor as CGColorRef
        let color2 = UIColor(red: 255/255, green: 255/255, blue: 255/255, alpha: 0.1).CGColor as CGColorRef
        let color3 = UIColor(red: 255/255, green: 255/255, blue: 255/255, alpha: 0.2).CGColor as CGColorRef
        let color4 = UIColor(red: 255/255, green: 255/255, blue: 255/255, alpha: 0.4).CGColor as CGColorRef
        gradientLayer.colors = [color1, color2, color3, color4]
        gradientLayer.locations = [0.0, 0.25, 0.75, 1.0]
        gradientLayer.startPoint = CGPoint(x: 0, y: 0)
        gradientLayer.endPoint = CGPoint(x: 0, y: 1)
        chartView.layer.addSublayer(gradientLayer)
        
        return chartView
    }
    
    
    //********************************************************************************************************
    
    /// get blood Presure Monitor Chart
    static public func bloodPressureMonitor(frame: CGRect, data: [[Double]], labels: [String]) ->UIView{
        var myView = UIView(frame: frame)
        
        let colors = [UIColor(red: 61/255, green: 180/255, blue: 195/255, alpha: 1),
            UIColor(red: 243/255, green: 153/255, blue: 34/255, alpha: 1),
            UIColor(red: 154/255, green: 108/255, blue: 176/255, alpha: 1),
            UIColor(red: 30/255, green: 250/255, blue: 30/255, alpha: 0.8),
            UIColor(red: 250/255, green: 30/255, blue: 30/255, alpha: 0.8),
            UIColor(red: 30/255, green: 30/255, blue: 30/255, alpha: 1),
            UIColor(red: 244/255, green: 170/255, blue: 103/255, alpha: 1),
            UIColor(red: 220/255, green: 250/255, blue: 250/255, alpha: 0)]
        
        
        var dataLabels: [String] = []
        for item in labels{
            dataLabels.append("")
            dataLabels.append(item)
        }
        dataLabels.append("")
        let values = data
        
        var xValues: [[Int]] = []
        for i in 0..<values.count{
            var tempXValues: [Int] = []
            for j in 0..<values[i].count{
                tempXValues.append(2 * j + 1)
            }
            xValues.append(tempXValues)
        }
        
        
        
        var dataEntries: [ [ChartDataEntry] ] = []
        for i in 0..<values.count{
            var entries: [ChartDataEntry] = []
            for j in 0..<values[i].count{
                let dataEntry = ChartDataEntry(value: values[i][j], xIndex: xValues[i][j])
                entries.append(dataEntry)
            }
            dataEntries.append(entries)
            
        }

        var chartView = LineChartView(frame: CGRect(x: 0.0, y: 0.0, width: frame.width, height: frame.height))
        
        var lineChartDataSets: [LineChartDataSet] = []
        lineChartDataSets.append(LineChartDataSet(yVals: dataEntries[0], label: "收缩�?))
        lineChartDataSets.append(LineChartDataSet(yVals: dataEntries[1], label: "舒张�?))
        lineChartDataSets.append(LineChartDataSet(yVals: dataEntries[2], label: "心率"))
        
        
        for i in 0..<lineChartDataSets.count{
            lineChartDataSets[i].colors = [colors[i]]
            lineChartDataSets[i].drawCircleHoleEnabled = false
            lineChartDataSets[i].circleColors = [colors[i]]
            lineChartDataSets[i].circleRadius = 3.5
//            lineChartDataSets[i].drawCubicEnabled = false
            lineChartDataSets[i].drawValuesEnabled = false
            lineChartDataSets[i].lineWidth = 1
        }
        
        
        //设置data

        let lineChartData = LineChartData(xVals: dataLabels, dataSets: lineChartDataSets)
        chartView.data = lineChartData
        
        //设置动画
        chartView.animate(xAxisDuration: 2.0, yAxisDuration: 2.0, easingOption: .Linear)
        
        //设置X Y 轴及网格参数
        
        chartView.xAxis.labelPosition = .Bottom
        chartView.xAxis.drawGridLinesEnabled = false
        chartView.xAxis.drawAxisLineEnabled = false
        chartView.xAxis.axisLineColor = colors[1]
        chartView.xAxis.axisLineWidth = 1
        chartView.xAxis.labelTextColor = colors[5]
        chartView.xAxis.spaceBetweenLabels = 1
        chartView.xAxis.avoidFirstLastClippingEnabled = true
        chartView.xAxis.setLabelsToSkip(0)
        chartView.xAxis
        
        chartView.rightAxis.enabled = false
        chartView.leftAxis.startAtZeroEnabled = false
        chartView.leftAxis.drawGridLinesEnabled = false
        chartView.leftAxis.customAxisMin = 40
        chartView.leftAxis.customAxisMax = 220
        chartView.leftAxis.drawAxisLineEnabled = false
        chartView.leftAxis.gridLineWidth = 1
        chartView.leftAxis.setLabelCount(7, force: true)
        chartView.leftAxis.labelFont = UIFont.systemFontOfSize(12)
        chartView.leftAxis.labelTextColor = colors[6]
        //  chartView.leftAxis.drawLabelsEnabled = false
        
        //value格式�?        var numberFormatter = NSNumberFormatter()
        numberFormatter.maximumFractionDigits = 0
        chartView.leftAxis.valueFormatter = numberFormatter
        
        //target line
        var targetLine90 = ChartLimitLine(limit: 90, label: "90")
        var targetLine140 = ChartLimitLine(limit: 140, label: "140")
        targetLine90.lineDashLengths = [3.0, 3.0]
        targetLine90.lineColor = colors[3]
        targetLine90.lineWidth = 0.5
        targetLine90.valueTextColor = colors[6]
        targetLine140.lineDashLengths = [3.0, 3.0]
        targetLine140.lineColor = colors[4]
        targetLine140.lineWidth = 0.5
        targetLine140.valueTextColor = colors[4]

        chartView.leftAxis.addLimitLine(targetLine90)
        chartView.leftAxis.addLimitLine(targetLine140)
        
        //设置图像description
        chartView.descriptionText = ""
        chartView.legend.position = ChartLegend.ChartLegendPosition.BelowChartLeft
        
        //background
        chartView.backgroundColor = colors[7]
//        chartView.gridBackgroundColor = UIColor.whiteColor()
        chartView.gridBackgroundColor = colors[7]
        
        //动作
        chartView.setScaleEnabled(false)
        
        chartView.notifyDataSetChanged()
        addBloodPressureMonitorBG(myView, contentFrame:chartView.contentRect)
        myView.addSubview(chartView)
    
        return myView
        
    }
    
    
    
    static private func addBloodPressureMonitorBG(targetView: UIView, contentFrame: CGRect) {
        
        //设置背景填充图层
        let totalHight = contentFrame.height
        let layer1Hight = (220.0 - 140.0) / (220.0 - 40.0) * totalHight
        let layer2Hight = (140.0 - 90.0) / (220.0 - 40.0)  * totalHight
        let layer3Hight = (90.0 - 40.0) / (220.0 - 40.0) * totalHight
        
        var bgLayer1 = CALayer()
        bgLayer1.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y, width: contentFrame.width, height: layer1Hight)
        bgLayer1.backgroundColor = UIColor(red: 255/255, green: 236/255, blue: 235/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer1)
        var bgLayer2 = CALayer()
        bgLayer2.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight, width: contentFrame.width, height: layer2Hight)
        bgLayer2.backgroundColor = UIColor(red: 254/255, green: 245/255, blue: 233/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer2)
        var bgLayer3 = CALayer()
        bgLayer3.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight + layer2Hight, width: contentFrame.width, height: layer3Hight)
        bgLayer3.backgroundColor = UIColor(red: 230/255, green: 252/255, blue: 237/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer3)
 

        
    }
    
    static private func addBodyFatMonitorBG(targetView: UIView, contentFrame: CGRect) {
        
        //设置背景填充图层
        let totalHight = contentFrame.height
        let layer1Hight = (120.0 - 80.0) / (120.0 - 0.0) * totalHight
        let layer2Hight = (80.0 - 40.0) / (120.0 - 0.0)  * totalHight
        let layer3Hight = (40.0 - 0.0) / (120.0 - 0.0) * totalHight
        
        var bgLayer1 = CALayer()
        bgLayer1.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y, width: contentFrame.width , height: layer1Hight)
        bgLayer1.backgroundColor = UIColor(red: 255/255, green: 236/255, blue: 235/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer1)
        var bgLayer2 = CALayer()
        bgLayer2.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight, width: contentFrame.width, height: layer2Hight)
        bgLayer2.backgroundColor = UIColor(red: 254/255, green: 245/255, blue: 233/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer2)
        var bgLayer3 = CALayer()
        bgLayer3.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight + layer2Hight, width: contentFrame.width, height: layer3Hight)
        bgLayer3.backgroundColor = UIColor(red: 230/255, green: 252/255, blue: 237/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer3)
        
    }
    
    static private func addBodyWeightBG(targetView: UIView, contentFrame: CGRect) {
        
        //设置背景填充图层
        let totalHight = contentFrame.height
        let layer1Hight = (100.0 - 80.0) / (100.0 - 10.0) * totalHight
        let layer2Hight = (80.0 - 60.0) / (100.0 - 10.0)  * totalHight
        let layer3Hight = (60.0 - 10.0) / (100.0 - 10.0) * totalHight
        
        var bgLayer1 = CALayer()
        bgLayer1.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y, width: contentFrame.width , height: layer1Hight)
        bgLayer1.backgroundColor = UIColor(red: 255/255, green: 236/255, blue: 235/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer1)
        var bgLayer2 = CALayer()
        bgLayer2.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight, width: contentFrame.width, height: layer2Hight)
        bgLayer2.backgroundColor = UIColor(red: 254/255, green: 245/255, blue: 233/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer2)
        var bgLayer3 = CALayer()
        bgLayer3.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight + layer2Hight, width: contentFrame.width, height: layer3Hight)
        bgLayer3.backgroundColor = UIColor(red: 230/255, green: 252/255, blue: 237/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer3)
        
    }
    
    static private func addBodyHeightBG(targetView: UIView, contentFrame: CGRect) {
        
        //设置背景填充图层
        let totalHight = contentFrame.height
        let layer1Hight = (210.0 - 150.0) / (210.0 - 30.0) * totalHight
        let layer2Hight = (150.0 - 90.0) / (210.0 - 30.0)  * totalHight
        let layer3Hight = (90.0 - 30.0) / (210.0 - 30.0) * totalHight
        
        var bgLayer1 = CALayer()
        bgLayer1.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y, width: contentFrame.width , height: layer1Hight)
        bgLayer1.backgroundColor = UIColor(red: 255/255, green: 236/255, blue: 235/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer1)
        var bgLayer2 = CALayer()
        bgLayer2.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight, width: contentFrame.width, height: layer2Hight)
        bgLayer2.backgroundColor = UIColor(red: 254/255, green: 245/255, blue: 233/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer2)
        var bgLayer3 = CALayer()
        bgLayer3.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight + layer2Hight, width: contentFrame.width, height: layer3Hight)
        bgLayer3.backgroundColor = UIColor(red: 230/255, green: 252/255, blue: 237/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer3)
        
    }
    
    static private func addBloodSugerMonitorBG(targetView: UIView, contentFrame: CGRect){
        let colors = [ UIColor(red: 255/255, green: 236/255, blue: 235/255, alpha: 1),
            UIColor(red: 230/255, green: 230/255, blue: 220/255, alpha: 1),
            UIColor(red: 230/255, green: 252/255, blue: 245/255, alpha: 1),
            UIColor(red: 255/255, green: 245/255, blue: 233/255, alpha: 1)]
            
        
        //设置背景填充图层
        let totalHight = contentFrame.height
        var layersHight: [CGFloat] = []
        layersHight.append((15.0 - 7.8) / (15.0 - 3.0) * totalHight)
        layersHight.append((7.8 - 6.1) / (15.0 - 3.0)  * totalHight)
        layersHight.append((6.1 - 3.9) / (15.0 - 3.0) * totalHight)
        layersHight.append ((3.9 - 3.0) / (15.0 - 3.0) * totalHight)
        
        var yOffset: CGFloat = 0.0
        for i in 0..<layersHight.count{
            var bgLayer = CALayer()
            
            bgLayer.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + yOffset, width: contentFrame.width, height: layersHight[i])
            bgLayer.backgroundColor = colors[i].CGColor
            targetView.layer.addSublayer(bgLayer)
            yOffset += layersHight[i]
            
        }
        
    }
    
    static private func addTemperatureBG(targetView: UIView, contentFrame: CGRect) {
        
        //设置背景填充图层
        let totalHight = contentFrame.height
        let layer1Hight = (41.5 - 37.0) / (41.5 - 35.5) * totalHight
        let layer2Hight = (37.0 - 36.0) / (41.5 - 35.5)   * totalHight
        let layer3Hight = (36.0 - 35.5) / (41.5 - 35.5)  * totalHight
        
        var bgLayer1 = CALayer()
        bgLayer1.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y, width: contentFrame.width , height: layer1Hight)
        bgLayer1.backgroundColor = UIColor(red: 255/255, green: 255/255, blue: 255/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer1)
        var bgLayer2 = CALayer()
        bgLayer2.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight, width: contentFrame.width, height: layer2Hight)
        bgLayer2.backgroundColor = UIColor(red: 230/255, green: 252/255, blue: 237/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer2)
        var bgLayer3 = CALayer()
        bgLayer3.frame = CGRect(x: contentFrame.origin.x, y: contentFrame.origin.y + layer1Hight + layer2Hight, width: contentFrame.width, height: layer3Hight)
        bgLayer3.backgroundColor = UIColor(red: 255/255, green: 255/255, blue: 255/255, alpha: 1).CGColor
        targetView.layer.addSublayer(bgLayer3)
        
    }
    
    
    static private func analysisChart3(#frame: CGRect, normalCount: Int, highCount: Int, lowCount: Int) ->PieChartView {
        
        var chartView = PieChartView(frame: frame)
        let colors = [
            UIColor(red: 255/255, green: 36/255, blue: 35/255, alpha: 1),
            UIColor(red: 254/255, green: 140/255, blue: 0/255, alpha: 1),
            UIColor(red: 15/255, green: 221/255, blue: 74/255, alpha: 1)
            ]
        
        var dataLabels: [String] = [" ", " ", " "]
        
        
        var dataEntries :[ChartDataEntry] = []
        var totalCount = normalCount + highCount + lowCount
        
        dataEntries.append(ChartDataEntry(value: Double(highCount)/Double(totalCount), xIndex: 0))
        dataEntries.append(ChartDataEntry(value: Double(lowCount)/Double(totalCount), xIndex: 1))
        dataEntries.append(ChartDataEntry(value: Double(normalCount)/Double(totalCount), xIndex: 2))
        
        
        //数据设置
        let pieChartDataSet = PieChartDataSet(yVals: dataEntries ,label: "")
        let pieChartData = PieChartData(xVals: dataLabels, dataSet: pieChartDataSet)
        pieChartDataSet.colors = colors
        //设置扇形区域字体
        pieChartDataSet.drawValuesEnabled = false
        chartView.data = pieChartData
        
        //动画设置
        chartView.animate(xAxisDuration: 2.0, yAxisDuration: 2.0)
        //        中心区域文字设置
        //        chartView.centerText = "\(values.count)\n 测量次数"
        //        chartView.centerTextFont = UIFont.systemFontOfSize(14)
        //中心区域比例
        chartView.holeRadiusPercent = 0.8
        //        //中心带边框比�?        //        chartView.transparentCircleRadiusPercent = 0.3
        chartView.descriptionText = ""
        //        chartView.backgroundColor = UIColor.brownColor()
        chartView.legend.enabled = false
        chartView.userInteractionEnabled = false
        
        return chartView
    }

}