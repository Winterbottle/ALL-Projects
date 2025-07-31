<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<html> 
    <head>
        <title>A2-T05G18-8550864,8535383(DarayuthCHEA,JeslynHo)</title>        

    <style>
    #table {
        border: 2px solid #000000;
        width: 80%;
        margin: 0 auto;
        text-align: center;
        align: center;
    }

    #table td {
        font-size: 18px;
        padding: 5px;
        height: 50px;
        align: center;
    }
    </style>

    </head>    
<body>
        <h1>
            <xsl:value-of select ="forecast/@queryLocation" />
            [<xsl:value-of select="forecast/@queryTime" />]
        </h1>

    <table  id="table" border="1">
        <tr bgcolor="#0099ff">
            <th width="10%">Date</th>
            <th width="10%">Mon</th>
            <th width="10%">Tue</th>
            <th width="10%">Wed</th>
            <th width="10%">Thu</th>
            <th width="10%">Fri</th>
            <th width="10%">Sat</th>
            <th width="10%">Sun</th>

        </tr>
        <xsl:for-each select="forecast/weather"> <!--a loop for each weather element-->
        <xsl:sort select="year"     data-type="number"  order="ascending"/>
        <xsl:sort select="month"    data-type="number"  order="ascending"/>
        <xsl:sort select="date"     data-type="number"  order="ascending"/>
        <xsl:variable name="mmm" select="month"/>  <!--extract the value of the "month" element and assign it as"$mmm" variable-->
        <xsl:variable name="dayOfWeek" select="dayOfWeek"/>
        <xsl:variable name="overallCode" select="overallCode"/>
        <tr>
            <td bgcolor="#0099ff">
                <xsl:value-of select="date"/> 
                
                <xsl:choose>
                    <xsl:when test="$mmm = '1'"> Jan</xsl:when> <!--check the value of '$mmm'and display it-->
                    <xsl:when test="$mmm = '2'"> Feb</xsl:when>
                    <xsl:when test="$mmm = '3'"> Mar</xsl:when>
                    <xsl:when test="$mmm = '4'"> Apr</xsl:when>
                    <xsl:when test="$mmm = '5'"> May</xsl:when>
                    <xsl:when test="$mmm = '6'"> Jun</xsl:when>
                    <xsl:when test="$mmm = '7'"> Jul</xsl:when>
                    <xsl:when test="$mmm = '8'"> Aug</xsl:when>
                    <xsl:when test="$mmm = '9'"> Sep</xsl:when>
                    <xsl:when test="$mmm = '10'"> Oct</xsl:when>
                    <xsl:when test="$mmm = '11'"> Nov</xsl:when>
                    <xsl:when test="$mmm = '12'"> Dec</xsl:when>
                </xsl:choose>
            </td>
            <td>                
                <xsl:choose> 
                    <xsl:when test="$dayOfWeek = 'Mon'">        <!--check if the day of the weel is the same to "Mon"-->
                        <xsl:value-of select="highest"/><sup>o</sup> - <xsl:value-of select="lowest"/><sup>o</sup><br/>                        
                        <xsl:choose>
                            <xsl:when test="$overallCode = 'cloudy'">
                                <img src="cloudy.png"/><br/>
                                <font face="courier" color="blue"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'partlySunny'">
                                <img src="partlySunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'rain'">
                                <img src="rain.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'sunny'">
                                <img src="sunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'thunderstorm'">
                                <img src="thunderstorm.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                        </xsl:choose>                        
                    </xsl:when>
                </xsl:choose>            
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="$dayOfWeek = 'Tue'">        <!--check if the day of the weel is the same to "Tue"-->
                        <xsl:value-of select="highest"/><sup>o</sup> - <xsl:value-of select="lowest"/><sup>o</sup><br/>
                        <xsl:choose>
                            <xsl:when test="$overallCode = 'cloudy'">
                                <img src="cloudy.png"/><br/>
                                <font face="courier" color="blue"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'partlySunny'">
                                <img src="partlySunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'rain'">
                                <img src="rain.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'sunny'">
                                <img src="sunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'thunderstorm'">
                                <img src="thunderstorm.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                        </xsl:choose>   
                    </xsl:when>
                </xsl:choose>
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="$dayOfWeek = 'Wed'">        <!--check if the day of the weel is the same to "Wed"-->
                        <xsl:value-of select="highest"/><sup>o</sup> - <xsl:value-of select="lowest"/><sup>o</sup><br/>
                        <xsl:choose>
                            <xsl:when test="$overallCode = 'cloudy'">
                                <img src="cloudy.png"/><br/>
                                <font face="courier" color="blue"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'partlySunny'">
                                <img src="partlySunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'rain'">
                                <img src="rain.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'sunny'">
                                <img src="sunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'thunderstorm'">
                                <img src="thunderstorm.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                        </xsl:choose>   
                    </xsl:when>
                </xsl:choose>  
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="$dayOfWeek = 'Thu'">        <!--check if the day of the weel is the same to "Thu"-->
                        <xsl:value-of select="highest"/><sup>o</sup> - <xsl:value-of select="lowest"/><sup>o</sup><br/>
                        <xsl:choose>
                            <xsl:when test="$overallCode = 'cloudy'">
                                <img src="cloudy.png"/><br/>
                                <font face="courier" color="blue"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'partlySunny'">
                                <img src="partlySunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'rain'">
                                <img src="rain.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'sunny'">
                                <img src="sunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'thunderstorm'">
                                <img src="thunderstorm.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                        </xsl:choose>   
                    </xsl:when>
                </xsl:choose>  
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="$dayOfWeek = 'Fri'">        <!--check if the day of the weel is the same to "Fri"-->
                        <xsl:value-of select="highest"/><sup>o</sup> - <xsl:value-of select="lowest"/><sup>o</sup><br/>
                        <xsl:choose>
                            <xsl:when test="$overallCode = 'cloudy'">
                                <img src="cloudy.png"/><br/>
                                <font face="courier" color="blue"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'partlySunny'">
                                <img src="partlySunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'rain'">
                                <img src="rain.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'sunny'">
                                <img src="sunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'thunderstorm'">
                                <img src="thunderstorm.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                        </xsl:choose>   
                    </xsl:when>
                </xsl:choose>  
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="$dayOfWeek = 'Sat'">        <!--check if the day of the weel is the same to "Sat"-->
                        <xsl:value-of select="highest"/><sup>o</sup> - <xsl:value-of select="lowest"/><sup>o</sup>
                        <xsl:choose>
                            <xsl:when test="$overallCode = 'cloudy'">
                                <img src="cloudy.png"/><br/>
                                <font face="courier" color="blue"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'partlySunny'">
                                <img src="partlySunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'rain'">
                                <img src="rain.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'sunny'">
                                <img src="sunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'thunderstorm'">
                                <img src="thunderstorm.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                        </xsl:choose>   
                    </xsl:when>
                </xsl:choose>  
            </td>
            <td>
                <xsl:choose>
                    <xsl:when test="$dayOfWeek = 'Sun'">       <!--check if the day of the weel is the same to "Sun"-->
                        <xsl:value-of select="highest"/><sup>o</sup> - <xsl:value-of select="lowest"/><sup>o</sup>
                        <xsl:choose>
                            <xsl:when test="$overallCode = 'cloudy'">
                                <img src="cloudy.png"/><br/>
                                <font face="courier" color="blue"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'partlySunny'">
                                <img src="partlySunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'rain'">
                                <img src="rain.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'sunny'">
                                <img src="sunny.png"/><br/>
                                <font face="courier" color="#FF1493"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                            <xsl:when test="$overallCode = 'thunderstorm'">
                                <img src="thunderstorm.png"/><br/>
                                <font face="courier" color="red"><xsl:value-of select="overall"/></font> 
                            </xsl:when>
                        </xsl:choose>   
                    </xsl:when>
                </xsl:choose>  
            </td>      
        </tr>
        </xsl:for-each>
    </table>
</body>
</html>
</xsl:template>
</xsl:stylesheet>