package Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.mycompany.estudiantes.Estudiantes;
/**
 *
 * @author josep
 */
public class EstudiantesTest {
    
   private Estudiantes estudiante;

    @BeforeEach
    void setUp() {
        estudiante = new Estudiantes("Ana");
    }

    @Test
    void testAgregarNotaValida() {
        estudiante.agregarNota(80);
        assertEquals(1, estudiante.getNotas().size());
        assertEquals(80, estudiante.getNotas().get(0));
    }

    @Test
    void testAgregarNotaInvalida() {
        assertThrows(IllegalArgumentException.class, () -> estudiante.agregarNota(-5));
        assertThrows(IllegalArgumentException.class, () -> estudiante.agregarNota(150));
    }

    @Test
    void testCalcularPromedio() {
        estudiante.agregarNota(80);
        estudiante.agregarNota(70);
        estudiante.agregarNota(90);
        assertEquals(80.0, estudiante.calcularPromedio(), 0.01);
    }

    @Test
    void testAprobo() {
        estudiante.agregarNota(80);
        estudiante.agregarNota(70);
        assertTrue(estudiante.aprobo());

        Estudiantes otro = new Estudiantes("Luis");
        otro.agregarNota(40);
        otro.agregarNota(50);
        assertFalse(otro.aprobo());
    }
    
    @Test
        void testPromedioFallido() {
            estudiante.agregarNota(100);
            estudiante.agregarNota(50);
            // El promedio real es 75.0
            assertEquals(80.0, estudiante.calcularPromedio(), 0.01);
        }
    
    @Test
        void testAproboFallido() {
            estudiante.agregarNota(40);
            estudiante.agregarNota(50);
            // El promedio real es 45, debería reprobar
            assertTrue(estudiante.aprobo());
        }
}
