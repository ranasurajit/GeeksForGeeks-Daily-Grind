<h2><a href="https://www.geeksforgeeks.org/problems/high-effort-vs-low-effort0213/1">High Effort vs Low Effort</a></h2><h3>Difficulty Level : Difficulty: Easy</h3><hr><div class="problems_problem_content__Xm_eO" style="--text-color: var(--problem-text-color);"><p><span style="font-size: 18px;">Given two integer arrays <strong>h[]</strong> and <strong>l[]</strong>, where h[i] and l[i] denote the number of tasks that can be completed on the<strong> i-th </strong>day by performing a high-effort task and a low-effort task, respectively.</span></p>
<p><span style="font-size: 18px;">For each day, you may choose exactly one of the following:</span></p>
<ul>
<li><span style="font-size: 18px;">Perform no task.</span></li>
<li><span style="font-size: 18px;">Perform a low-effort task.</span></li>
<li><span style="font-size: 18px;">Perform a high-effort task, which can only be performed on the first day or if no task was performed on the previous day.</span><span style="font-size: 18px;"><br></span></li>
</ul>
<p><span style="font-size: 18px;">Return the <strong>maximum</strong> total number of tasks that can be completed over all days.</span></p>
<p><span style="font-size: 18px;"><strong>Examples:</strong></span></p>
<pre><span style="font-size: 18px;"><strong>Input: </strong>h[] = [2, 8, 1], l[] = [1, 2, 1]
<strong>Output:</strong> 9
<strong>Explanation</strong>: Pick tasks h[1] and l[2]. Hence total = 9.</span>
</pre>
<pre><span style="font-size: 18px;"><strong>Input</strong>: h[] = [3, 6, 8, 7, 6], l[] = [1, 5, 4, 5, 3]
<strong>Output:</strong> 20
<strong>Explanation</strong>: Pick high-effort task on first day and low-effort task on all remaining days.
</span></pre>
<p><span style="font-size: 18px;"><strong>Constraints:</strong><br>1 ≤ h.size() = l.size() ≤ 10<sup>5</sup><br>0 </span><span style="font-size: 18px;">≤ h[i], l[i] </span><span style="font-size: 18px;">≤ 10<sup>3</sup><br></span></p></div><br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Dynamic Programming</code>&nbsp;<code>Algorithms</code>&nbsp;