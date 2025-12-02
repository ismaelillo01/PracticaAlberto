<?xml version="1.0" encoding="UTF-8"?>
<!--
Plantilla XSL hecha por inteligencia artificial.
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <title>Resumen Diario de Noticias</title>
                <style>
                    body { font-family: Arial, sans-serif; margin: 40px; background-color: #f4f4f9; }
                    h1 { color: #333; border-bottom: 2px solid #0056b3; padding-bottom: 10px; }
                    .noticia { background: white; border: 1px solid #ddd; margin-bottom: 15px; padding: 15px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
                    .noticia h2 { font-size: 18px; margin-top: 0; }
                    .noticia a { color: #0056b3; text-decoration: none; }
                    .noticia a:hover { text-decoration: underline; }
                    .meta { font-size: 12px; color: #666; margin-top: 10px; }
                    .fecha { font-weight: bold; color: #d9534f; }
                    .footer { margin-top: 30px; font-size: 0.9em; color: #777; text-align: center; }
                </style>
            </head>
            <body>
                <h1>Resumen de Noticias del Día</h1>
                <div id="contenedor-noticias">
                    <xsl:for-each select="resumen/noticia">
                        <div class="noticia">
                            <h2>
                                <a href="{link}" target="_blank">
                                    <xsl:value-of select="titulo"/>
                                </a>
                            </h2>
                            <div class="meta">
                                Fuente: <strong><xsl:value-of select="fuente"/></strong> |
                                Publicado: <span class="fecha"><xsl:value-of select="fechaString"/></span>
                            </div>
                        </div>
                    </xsl:for-each>
                </div>

                <xsl:if test="count(resumen/noticia) = 0">
                    <p>No se han encontrado noticias para el día de hoy.</p>
                </xsl:if>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>