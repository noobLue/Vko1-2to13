package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto toinenVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);

        toinenVarasto = new Varasto(10, 5);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaaminenEiTaytaYli(){
        toinenVarasto.lisaaVarastoon(11);

        assertEquals(0, toinenVarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenEiVoiOttaaLiikaa(){
        toinenVarasto.otaVarastosta(10);

        assertEquals(0, toinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiOleMahdottomiaVarastoja(){
        Varasto v = new Varasto(-1);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiOleMahdottomiaVarastoja2(){
        Varasto v = new Varasto(-1, 1);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void eiOleNegatiivistaAlkuSaldoa(){
        Varasto v = new Varasto(1, -1);

        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void eiVoiOttaaNegatiivisaMaaria(){
        varasto.lisaaVarastoon(5);

        varasto.otaVarastosta(-1);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void eiVoiLisataNegatiivisaMaaria(){
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void merkkiesitysSisaltaaSaldon(){

        assertTrue(varasto.toString().contains("saldo = " + varasto.getSaldo() + ","));
    }

    @Test
    public void merkkiesitysKertooPaljonMahtuu(){
        assertTrue(varasto.toString().endsWith("tilaa " + varasto.paljonkoMahtuu()));
    }

    @Test
    public void circlejiFail(){
        assertTrue(false);
    }
}