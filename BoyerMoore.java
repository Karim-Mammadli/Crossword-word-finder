import java.util.Hashtable;
import java.util.Set;

public class BoyerMoore
{
	/**
	 * The lastOccurance function
	 * @param S
	 * @return
	 */
	public static Hashtable<Character, Integer> lastOccurrenceFunction(String S)
	{
		Hashtable<Character, Integer> L = new Hashtable<Character, Integer>();
		int m = S.length();
		for (int i = m - 1; i >= 0; i -= 1)
		{
			char c = S.charAt(i);
			L.put(c, L.getOrDefault(c, i));
		}
		return L;
	}

	/**
	 * Run the Boyer Moore Pattern Matching
	 * @param T
	 * @param P
	 * @return
	 */
	public static int find(String T, String P)
	{
		int n = T.length();
		int m = P.length();
		Hashtable<Character, Integer> L = lastOccurrenceFunction(P);
		int i = m - 1;
		int j = m - 1;
		do
		{
			if (T.charAt(i) == P.charAt(j))
			{
				if (j == 0)
				{
					return i;
				}
				else
				{
					i -= 1;
					j -= 1;
				}
			}
			else
			{
				int l = L.getOrDefault(T.charAt(i), -1);
				i = i + m - Math.min(j, 1 + l);
				j = m - 1;
			}
		} while (!(i > n - 1));
		return -1;
	}
}
