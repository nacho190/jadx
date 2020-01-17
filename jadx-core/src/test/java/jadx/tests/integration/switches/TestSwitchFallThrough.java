package jadx.tests.integration.switches;

import org.junit.jupiter.api.Test;

import jadx.NotYetImplemented;
import jadx.tests.api.IntegrationTest;

import static jadx.tests.api.utils.assertj.JadxAssertions.assertThat;

public class TestSwitchFallThrough extends IntegrationTest {

	public static class TestCls {
		public int r;

		public void test(int a) {
			int i = 10;
			switch (a) {
				case 1:
					i = 1000;
					// fallthrough
				case 2:
					r = i;
					break;

				default:
					r = -1;
					break;
			}
			r *= 2;
			System.out.println("in: " + a + ", out: " + r);
		}

		public int testWrap(int a) {
			r = 0;
			test(a);
			return r;
		}

		public void check() {
			assertThat(testWrap(1)).isEqualTo(2000);
			assertThat(testWrap(2)).isEqualTo(20);
			assertThat(testWrap(0)).isEqualTo(-2);
		}
	}

	@NotYetImplemented("switch fallthrough")
	@Test
	public void test() {
		assertThat(getClassNode(TestCls.class))
				.code()
				.containsOnlyOnce("switch");
		// code correctness checks done in 'check' method
	}
}
